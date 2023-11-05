package com.ctytech.gameszone.service;

import java.util.List;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.exception.GameszoneException;

public interface GameService {

    String createNewGame(GameDTO gameDTO) throws GameszoneException;

    GameDTO getGame(Integer gameId) throws GameszoneException;

    List<GameDTO> searchGames(String gameName) throws GameszoneException;
}
