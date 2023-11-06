package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.service.GameService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

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

    @GetMapping()
    public ResponseEntity<GameDTO> getGame(@RequestParam(required = false, name = "gameId") Integer gameId,
            @RequestParam(required = false, name = "gameName") String gameName)
            throws GameszoneException {

        GameDTO gameDTO = new GameDTO();

        if (gameId != null) {
            gameDTO = gameService.getGame(gameId);
        } else if (gameName != null) {
            gameDTO = gameService.getGame(gameName);
        } else {
            throw new GameszoneException("GameAPI.QUERY_NOT_PROVIDED");
        }

        return new ResponseEntity<GameDTO>(gameDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<GameDTO>> searchGames(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "pageNo", defaultValue = "0") @Pattern(regexp = "^[0-9]*$", message = "{page.pageno.invalid}") String pageNo,
            @RequestParam(name = "limit", defaultValue = "10") @Pattern(regexp = "^[0-9]*$", message = "{page.limit.invalid}") @Min(value = 1, message = "{page.limit.min}") String limit)
            throws GameszoneException {

        Page<GameDTO> gamepage = gameService.searchGames(query, Integer.parseInt(pageNo), Integer.parseInt(limit));

        return new ResponseEntity<>(gamepage, HttpStatus.OK);
    }
}