package com.ctytech.gameszone.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.ctytech.gameszone.constants.BookingStatus;
import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.dto.OptionDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.record.SlotAvailabilityRecord;

public interface BookingService {

        BookingDTO createNewBooking(BookingDTO bookingDTO) throws GameszoneException;

        BookingDTO updateBookingStatus(Integer bookingId, BookingStatus status) throws GameszoneException;

        SlotAvailabilityRecord fetchSlotAvailability(Integer slotId, Integer gameId, LocalDate forDate)
                        throws GameszoneException;

        List<SlotAvailabilityRecord> fetchGameSlotsWithAvailabilityStatus(Integer gameId, LocalDate forDate)
                        throws GameszoneException;

        Set<OptionDTO> getBookingEnabledDates() throws GameszoneException;

        Page<BookingDTO> searchBookings(
                        String forDate,
                        String bookingStatus,
                        String bookingId,
                        String userId,
                        String gameId,
                        Integer pageNo,
                        Integer resultsPerPage, String sort, List<String> includes)
                        throws GameszoneException;
}
