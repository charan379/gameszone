package com.ctytech.gameszone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.entity.Game;
import com.ctytech.gameszone.entity.Slot;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.GameRepository;

import jakarta.transaction.Transactional;

@Service(value = "gameService")
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameDTO createNewGame(GameDTO gameDTO) throws GameszoneException {

        // Check if Game with same name already exists
        Optional<Game> gameNameExist = gameRepository.findByGameName(gameDTO.getGameName());
        if (gameNameExist.isPresent()) {
            throw new GameszoneException("GameService.GAMENAME_ALREADY_EXIST");
        }

        // if not exits then create new game
        Game game = new Game();
        //
        game.setGameName(gameDTO.getGameName());
        game.setImage(gameDTO.getImage());
        //
        return gameRepository.save(game).toDto();
    }

    @Override
    public GameDTO getGame(Integer gameId) throws GameszoneException {
        //
        Optional<Game> game = gameRepository.findById(gameId);
        //
        if (game.isPresent()) {
            //
            return game.get().toDto();
        } else {
            //
            throw new GameszoneException("GameService.GAME_NOT_FOUND");
        }

    }

    @Override
    public GameDTO getGame(String gameName) throws GameszoneException {
        //
        Optional<Game> game = gameRepository.findByGameName(gameName);
        //
        if (game.isPresent()) {
            return game.get().toDto();
        } else {
            //
            throw new GameszoneException("GameService.GAME_NOT_FOUND");
        }

    }

    @Override
    public Page<GameDTO> searchGames(String gameName, Integer pageNo, Integer resultsPerPage)
            throws GameszoneException {

        Pageable pageable = PageRequest.of(pageNo, resultsPerPage, Sort.by(Sort.Direction.ASC, "gameName"));

        Page<Game> gamePage = gameRepository.searchGameByName(gameName, pageable);

        Page<GameDTO> gPage = gamePage.map(game -> game.toDto());

        return gPage;
    }

    @Override
    public GameDTO updateGameNameById(Integer gameId, String newGameName) throws GameszoneException {

        // Check if GameName already exits
        Optional<Game> gameNameExits = gameRepository.findByGameName(newGameName);
        //
        if (gameNameExits.isPresent() && !(gameNameExits.get().getGameId().equals(gameId))) {
            throw new GameszoneException("GameService.GAMENAME_ALREADY_EXIST");
        }
        //
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        //
        Game gameToBeUpdated = optionalGame.orElseThrow(() -> new GameszoneException("GameService.GAMEID_NOT_FOUND"));
        //
        gameToBeUpdated.setGameName(newGameName);
        //
        return gameToBeUpdated.toDto();
    }

    @Override
    public GameDTO updateGameImageById(Integer gameId, String newGameImage) throws GameszoneException {
        //
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        //
        Game gameToBeUpdated = optionalGame.orElseThrow(() -> new GameszoneException("GameService.GAMEID_NOT_FOUND"));
        //
        gameToBeUpdated.setImage(newGameImage);
        //
        return gameToBeUpdated.toDto();
    }

    @Override
    public void addGameSlot(Integer gameId, Slot newSlot) throws GameszoneException {
        //
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        //
        Game game = optionalGame.orElseThrow(() -> new GameszoneException("GameService.GAME_NOT_FOUND"));
        //
        game.getSlots().add(newSlot);
    }

}
