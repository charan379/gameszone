package com.ctytech.gameszone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.dto.SlotDTO;
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
        List<Slot> slots = new ArrayList<Slot>();
        //
        game.setGameName(gameDTO.getGameName());
        game.setImage(gameDTO.getImage());
        // If SlotDTOs is not an Empty [] or not null then
        if (gameDTO.getSlots() != null && !gameDTO.getSlots().isEmpty()) {
            for (SlotDTO slotDTO : gameDTO.getSlots()) {
                Slot slot = new Slot();
                slot.setSlotName(slotDTO.getSlotName());
                slot.setStartTime(slotDTO.getStartTime());
                slot.setEndTime(slotDTO.getEndTime());
                slots.add(slot);
            }
        }
        //
        game.setSlots(slots);
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

}
