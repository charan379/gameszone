package com.ctytech.gameszone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

        //
        Game game = new Game();
        //
        List<Slot> slots = new ArrayList<Slot>();
        //
        game.setGameName(gameDTO.getGameName());
        game.setImage(gameDTO.getImage());
        //
        gameDTO.getSlots().stream().forEach(slotDTO -> {
            Slot slot = new Slot();
            slot.setSlotName(slotDTO.getSlotName());
            slot.setStartTime(slotDTO.getStartTime());
            slot.setEndTime(slotDTO.getEndTime());
            slots.add(slot);
        });
        //
        game.setSlots(slots);
        //
        return gameRepository.save(game).toDto();
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
