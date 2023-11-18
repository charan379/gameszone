package com.ctytech.gameszone.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctytech.gameszone.constants.BookingStatus;
import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.entity.Booking;
import com.ctytech.gameszone.entity.Game;
import com.ctytech.gameszone.entity.Slot;
import com.ctytech.gameszone.entity.User;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.record.SlotAvailabilityRecord;
import com.ctytech.gameszone.repository.BookingRepository;
import com.ctytech.gameszone.repository.GameRepository;
import com.ctytech.gameszone.repository.SlotRepository;
import com.ctytech.gameszone.repository.UserRepository;

@Service(value = "bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BookingDTO createNewBooking(BookingDTO bookingDTO) throws GameszoneException {

        // check if the slot of booking game is already booked on required day or not
        // fetch slotAvailabilityRecord
        SlotAvailabilityRecord slotAvailabilityRecord = fetchSlotAvailability(bookingDTO.getSlotId(),
                bookingDTO.getGameId(), bookingDTO.getForDate());

        // if already booked throw error
        if (slotAvailabilityRecord.isBooked())
            throw new GameszoneException("BookingService.SLOT_ALREADY_BOOKED");

        // if not then book slot
        Booking booking = new Booking();

        Optional<Game> game = gameRepository.findById(bookingDTO.getGameId());

        Optional<Slot> slot = slotRepository.findById(bookingDTO.getSlotId());

        Optional<User> user = userRepository.findByUserIdWithOutPassword(bookingDTO.getUserId());

        booking.setForDate(bookingDTO.getForDate());
        booking.setTransactionDate(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.REQUESTED);
        booking.setGame(game.orElseThrow(() -> new GameszoneException("GameService.GAME_NOT_FOUND")));
        booking.setSlot(slot.orElseThrow(() -> new GameszoneException("SlotService.SLOT_NOT_FOUND")));
        booking.setUser(user.orElseThrow(() -> new GameszoneException("UserService.USER_NOT_FOUND")));

        return bookingRepository.save(booking).tDto();
    }

    @Override
    public SlotAvailabilityRecord fetchSlotAvailability(Integer slotId, Integer gameId, LocalDate forDate)
            throws GameszoneException {

        boolean isSlotBooked;

        Optional<Booking> booking = bookingRepository
                .findByBookingDateAndGameIdAndSlotId(forDate, gameId, slotId);

        SlotDTO slot = new SlotDTO();

        Optional<Slot> optionalSlot = slotRepository.findByGameIdAndSlotId(gameId, slotId);

        if (optionalSlot.isPresent()) {
            slot.setAll(optionalSlot.get().toDto());
        } else {
            throw new GameszoneException("BookingService.SLOT_NOT_FOUND_GAMEID_SLOTID");

        }

        isSlotBooked = booking.isPresent() && new ArrayList<BookingStatus>() {
            {
                add(BookingStatus.APPROVED);
                add(BookingStatus.REQUESTED);
            }
        }.contains(booking.get().getBookingStatus());

        // availability
        SlotAvailabilityRecord slotAvailabilityRecord = new SlotAvailabilityRecord(slot, isSlotBooked,
                forDate);

        return slotAvailabilityRecord;
    }

    @Override
    public List<SlotAvailabilityRecord> fetchGameSlotsWithAvailabilityStatus(Integer gameId, LocalDate forDate)
            throws GameszoneException {

        GameDTO gameDTO = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameszoneException("BookingService.GAME_NOT_FOUND")).toDto(true);

        List<SlotAvailabilityRecord> slotsWithAvailabilityStatus = new ArrayList<SlotAvailabilityRecord>();

        if (gameDTO.getSlots() != null && !gameDTO.getSlots().isEmpty())
            for (SlotDTO slotDTO : gameDTO.getSlots()) {
                try {
                    SlotAvailabilityRecord slotAvailabilityRecord = fetchSlotAvailability(slotDTO.getSlotId(), gameId,
                            forDate);
                    slotsWithAvailabilityStatus.add(slotAvailabilityRecord);
                } catch (Exception e) {
                    continue;
                }
            }

        return slotsWithAvailabilityStatus;
    }

}
