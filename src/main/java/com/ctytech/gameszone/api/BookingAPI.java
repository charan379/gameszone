package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.service.BookingService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/booking")
public class BookingAPI {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    public ResponseEntity<BookingDTO> bookSlot(@RequestBody @Valid BookingDTO bookingDTO) throws GameszoneException {

        BookingDTO newBookingDTO = bookingService.createNewBooking(bookingDTO);

        return new ResponseEntity<BookingDTO>(newBookingDTO, HttpStatus.CREATED);
    }
}
