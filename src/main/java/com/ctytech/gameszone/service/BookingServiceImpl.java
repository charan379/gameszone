package com.ctytech.gameszone.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.entity.Booking;
import com.ctytech.gameszone.entity.Game;
import com.ctytech.gameszone.entity.Slot;
import com.ctytech.gameszone.entity.User;
import com.ctytech.gameszone.exception.GameszoneException;
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

        Booking booking = new Booking();

        Optional<Game> game = gameRepository.findById(bookingDTO.getGameId());

        Optional<Slot> slot = slotRepository.findById(bookingDTO.getSlotId());

        Optional<User> user = userRepository.findByUserIdWithOutPassword(bookingDTO.getUserId());

        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setTransactionDate(LocalDateTime.now());
        booking.setGame(game.orElseThrow(() -> new GameszoneException("GameService.GAME_NOT_FOUND")));
        booking.setSlot(slot.orElseThrow(() -> new GameszoneException("SlotService.SLOT_NOT_FOUND")));
        booking.setUser(user.orElseThrow(() -> new GameszoneException("UserService.USER_NOT_FOUND")));

        return bookingRepository.save(booking).tDto();
    }

}
