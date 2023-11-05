package com.ctytech.gameszone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

}
