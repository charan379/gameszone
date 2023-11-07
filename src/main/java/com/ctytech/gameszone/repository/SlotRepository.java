package com.ctytech.gameszone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.Slot;

@Repository
public interface SlotRepository extends CrudRepository<Slot, Integer> {

    @Query(value = "SELECT * FROM slot s WHERE s.game_id = :gameId", nativeQuery = true)
    List<Slot> findByGameId(@Param("gameId") Integer gameId);
}
