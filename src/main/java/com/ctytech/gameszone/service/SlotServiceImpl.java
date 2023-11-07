package com.ctytech.gameszone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.entity.Slot;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.SlotRepository;

import jakarta.transaction.Transactional;

@Service(value = "slotService")
@Transactional
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private GameService gameService;

    @Override
    public List<SlotDTO> getSlotsByGameId(Integer gameId) throws GameszoneException {
        //
        List<Slot> slots = slotRepository.findByGameId(gameId);
        //
        List<SlotDTO> slotDTOs = new ArrayList<SlotDTO>();
        //
        for (Slot slot : slots) {
            slotDTOs.add(slot.toDto());
        }
        //
        return slotDTOs;
    }

    @Override
    public SlotDTO updateSlot(Integer gameId, Integer slotId, SlotDTO update) throws GameszoneException {

        GameDTO game = gameService.getGame(gameId);

        Optional<Slot> optionalSlot = slotRepository.findById(slotId);

        Slot slotToBeUpdated = optionalSlot.orElseThrow(() -> new GameszoneException("SlotService.SLOT_NOT_FOUND"));

        // Validate SlotName
        validateSlotName(game.getSlots(), slotId, update.getSlotName());
        // update
        slotToBeUpdated.setSlotName(update.getSlotName());
        slotToBeUpdated.setStartTime(update.getStartTime());
        slotToBeUpdated.setEndTime(update.getEndTime());
        //
        return slotToBeUpdated.toDto();
    }

    // validate slot name
    private void validateSlotName(List<SlotDTO> existingSlots, Integer slotId, String slotName)
            throws GameszoneException {

        for (SlotDTO slotDTO : existingSlots) {
            if (slotDTO.getSlotName().toLowerCase().equals(slotName.toLowerCase())
                    && !slotDTO.getSlotId().equals(slotId)) {
                throw new GameszoneException("SlotService.SLOTNAME_ALREADY_EXIST");
            } else {
                continue;
            }
        }

    }
}
