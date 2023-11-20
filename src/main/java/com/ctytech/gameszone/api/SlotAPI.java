package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.service.SlotService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping(value = "/game")
public class SlotAPI {

    @Autowired
    private SlotService slotService;

    @PutMapping(value = "/{gameId}/update/slot/{slotId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SlotDTO> updateSlot(
            @PathVariable(name = "gameId") @Pattern(regexp = "^[0-9]*$", message = "{game.gameId.invalid}") String gameId,
            @PathVariable(name = "slotId") @Pattern(regexp = "^[0-9]*$", message = "{slot.slotId.invalid}") String slotId,
            @RequestBody @Valid SlotDTO slot) throws GameszoneException {
        //
        SlotDTO updatedSlot = slotService.updateSlot(Integer.parseInt(gameId), Integer.parseInt(slotId), slot);
        //
        return new ResponseEntity<SlotDTO>(updatedSlot, HttpStatus.OK);
    }

    @PostMapping(value = "/{gameId}/add/slot")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SlotDTO> addSlot(
            @PathVariable(name = "gameId") @Pattern(regexp = "^[0-9]*$", message = "{game.gameId.invalid}") String gameId,
            @RequestBody @Valid SlotDTO slot)
            throws GameszoneException {

        SlotDTO newSlot = slotService.addSlot(Integer.parseInt(gameId), slot);

        return new ResponseEntity<SlotDTO>(newSlot, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/slot/{slotId}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<String> deleteSlot(
            @PathVariable(name = "slotId") @Pattern(regexp = "^[0-9]*$", message = "{slot.slotId.invalid}") String slotId)
            throws GameszoneException {

        slotService.deleteSlot(Integer.parseInt(slotId));

        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }
}
