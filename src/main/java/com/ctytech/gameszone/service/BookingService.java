package com.ctytech.gameszone.service;

import java.time.LocalDate;
import java.util.List;

import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.record.SlotAvailabilityRecord;

public interface BookingService {

    BookingDTO createNewBooking(BookingDTO bookingDTO) throws GameszoneException;

    SlotAvailabilityRecord fetchSlotAvailability(Integer slotId, Integer gameId, LocalDate forDate)
            throws GameszoneException;

    List<SlotAvailabilityRecord> fetchGameSlotsWithAvailabilityStatus(Integer gameId, LocalDate forDate)
            throws GameszoneException;
}
