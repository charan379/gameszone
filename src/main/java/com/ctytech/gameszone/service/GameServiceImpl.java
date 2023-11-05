package com.ctytech.gameszone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.GameRepository;

@Service(value = "gameService")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public String createNewGame(GameDTO gameDTO) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNewGame'");
    }

    @Override
    public GameDTO getGame(Integer gameId) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGame'");
    }

    @Override
    public List<GameDTO> searchGames(String gameName) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchGames'");
    }

}
