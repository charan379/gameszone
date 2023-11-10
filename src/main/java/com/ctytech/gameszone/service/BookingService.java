package com.ctytech.gameszone.service;

import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.exception.GameszoneException;

public interface BookingService {

    BookingDTO createNewBooking(BookingDTO bookingDTO) throws GameszoneException;
}
