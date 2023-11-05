package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.service.GameService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "game")
public class GameAPI {

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/add")
    public ResponseEntity<GameDTO> postNewGame(@RequestBody @Valid GameDTO gameDTO) throws GameszoneException {

        GameDTO newGame = gameService.createNewGame(gameDTO);

        return new ResponseEntity<GameDTO>(newGame, HttpStatus.CREATED);
    }
}
