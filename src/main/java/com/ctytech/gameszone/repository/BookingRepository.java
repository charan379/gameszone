package com.ctytech.gameszone.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM booking b WHERE b.booking_date= ?1 AND b.game_id = ?2 AND b.slot_id = ?3", nativeQuery = true)
    Optional<Booking> findByBookingDateAndGameIdAndSlotId(LocalDate bookingDate, Integer gameId, Integer slotId);
}
