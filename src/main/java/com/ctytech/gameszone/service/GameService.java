package com.ctytech.gameszone.service;

import org.springframework.data.domain.Page;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.entity.Slot;
import com.ctytech.gameszone.exception.GameszoneException;

public interface GameService {

    GameDTO createNewGame(GameDTO gameDTO) throws GameszoneException;

    GameDTO getGame(Integer gameId) throws GameszoneException;

    GameDTO getGame(String gameName) throws GameszoneException;

    Page<GameDTO> searchGames(String gameName, Integer pageNo, Integer resultsPerPage) throws GameszoneException;

    GameDTO updateGameNameById(Integer gameId, String newGameName) throws GameszoneException;

    GameDTO updateGameImageById(Integer gameId, String newGameImage) throws GameszoneException;

    void addGameSlot(Integer gameId, Slot newSlot) throws GameszoneException;
}
