package com.ctytech.gameszone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

}
