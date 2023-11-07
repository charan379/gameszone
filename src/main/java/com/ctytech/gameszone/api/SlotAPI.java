package com.ctytech.gameszone.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.exception.GameszoneException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping(value = "/game")
public class SlotAPI {

    @PutMapping(value = "/{gameId}/update/slot/{slotId}")
    public ResponseEntity<SlotDTO> updateSlot(
            @PathVariable(name = "gameId") @Pattern(regexp = "^[0-9]*$", message = "{game.gameId.invalid}") String gameId,
            @PathVariable(name = "slotId") @Pattern(regexp = "^[0-9]*$", message = "{slot.slotId.invalid}") String slotId,
            @RequestBody @Valid SlotDTO slot) throws GameszoneException {

        return new ResponseEntity<SlotDTO>(slot, HttpStatus.OK);
    }
}
