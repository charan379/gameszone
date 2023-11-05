package com.ctytech.gameszone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.Slot;

@Repository
public interface SlotRepository extends CrudRepository<Slot, Integer> {

}
