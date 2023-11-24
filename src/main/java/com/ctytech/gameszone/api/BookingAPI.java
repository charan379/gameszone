package com.ctytech.gameszone.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.BookingDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.record.SlotAvailabilityRecord;
import com.ctytech.gameszone.service.BookingService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

    @GetMapping(value = "/slot/availability")
    public ResponseEntity<SlotAvailabilityRecord> getSlotAvailabilty(
            @RequestParam(name = "forDate") @NotNull(message = "{booking.bookingdate.absent}") @Pattern(regexp = "(?:[0-9]{1,2})-(0[1-9]|1[012])-(20)\\d{2}", message = "{booking.fordate.invalid}") String forDate,
            @RequestParam(name = "gameId") @Pattern(regexp = "^[0-9]*$", message = "{game.gameId.invalid}") String gameId,
            @RequestParam(name = "slotId") @Pattern(regexp = "^[0-9]*$", message = "{slot.slotId.invalid}") String slotId)
            throws GameszoneException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        SlotAvailabilityRecord slotAvailabilityRecord = bookingService.fetchSlotAvailability(Integer.parseInt(slotId),
                Integer.parseInt(gameId), LocalDate.parse(forDate, dateTimeFormatter));

        return new ResponseEntity<SlotAvailabilityRecord>(slotAvailabilityRecord, HttpStatus.OK);
    }

    @GetMapping(value = "/game/{gameId}/slots/availability")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<SlotAvailabilityRecord>> getSlotsAvailabilty(
            @RequestParam(name = "forDate", defaultValue = "today") @NotNull(message = "{booking.bookingdate.absent}") @Pattern(regexp = "(?:[0-9]{1,2})-(0[1-9]|1[012])-(2)\\d{3}|today$", message = "{booking.fordate.invalid}") String forDate,
            @PathVariable(name = "gameId") @Pattern(regexp = "^[0-9]*$", message = "{game.gameId.invalid}") String gameId)
            throws GameszoneException {

        LocalDate bookingForDate;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (forDate.trim().equals("today")) {
            bookingForDate = LocalDate.now();
        } else {
            bookingForDate = LocalDate.parse(forDate, dateTimeFormatter);
        }

        List<SlotAvailabilityRecord> listOfSlotAvailabilityRecords = bookingService
                .fetchGameSlotsWithAvailabilityStatus(Integer.parseInt(gameId),
                        bookingForDate);

        return new ResponseEntity<List<SlotAvailabilityRecord>>(listOfSlotAvailabilityRecords, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<BookingDTO>> getBookingsPage(
            @RequestParam(name = "pageNo", defaultValue = "0") @Pattern(regexp = "^[0-9]*$", message = "{page.pageno.invalid}") String pageNo,
            @RequestParam(name = "limit", defaultValue = "10") @Pattern(regexp = "^[0-9]*$", message = "{page.limit.invalid}") @Min(value = 1, message = "{page.limit.min}") String limit,
            @RequestParam(required = false, name = "include", defaultValue = "") List<String> include)
            throws GameszoneException {

        Page<BookingDTO> bookingsPage = bookingService.searchBookings(Integer.valueOf(pageNo), Integer.valueOf(limit),
                include);

        return new ResponseEntity<Page<BookingDTO>>(bookingsPage, HttpStatus.OK);

    }
}
