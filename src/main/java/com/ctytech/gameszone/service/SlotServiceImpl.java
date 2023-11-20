package com.ctytech.gameszone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctytech.gameszone.dto.GameDTO;
import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.entity.Slot;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.SlotRepository;

@Service(value = "slotService")
@Transactional
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private GameService gameService;

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public SlotDTO updateSlot(Integer gameId, Integer slotId, SlotDTO update) throws GameszoneException {

        GameDTO game = gameService.getGame(gameId, true);

        Optional<Slot> optionalSlot = slotRepository.findById(slotId);

        Slot slotToBeUpdated = optionalSlot.orElseThrow(() -> new GameszoneException("SlotService.SLOT_NOT_FOUND"));

        // Validate SlotName
        validateSlotName(game.getSlots(), slotId, update.getSlotName());
        // update
        slotToBeUpdated.setSlotName(update.getSlotName());
        slotToBeUpdated.setStartTime(update.getStartTime());
        slotToBeUpdated.setEndTime(update.getEndTime());
        slotToBeUpdated.setLocation(update.getLocation());
        //
        return slotToBeUpdated.toDto();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SlotDTO addSlot(Integer gameId, SlotDTO newSlot) throws GameszoneException {

        GameDTO game = gameService.getGame(gameId, true);
        // validate slotName
        validateSlotName(game.getSlots(), null, newSlot.getSlotName());
        //
        Slot slot = new Slot();
        //
        slot.setSlotName(newSlot.getSlotName());
        slot.setStartTime(newSlot.getStartTime());
        slot.setEndTime(newSlot.getEndTime());
        slot.setLocation(newSlot.getLocation());
        //
        gameService.addGameSlot(gameId, slot);
        //

        Slot newlyAddedSlot = slotRepository.findByGameId(gameId).stream()
                .filter(s -> s.getSlotName().equals(slot.getSlotName())).findFirst()
                .orElseThrow(() -> new GameszoneException("SlotService.SLOT_ADDED_BUT_CANT_RETRIVE_ID"));

        //
        SlotDTO newlyAddedSlotDTO = newlyAddedSlot.toDto();
        newlyAddedSlotDTO.setGameId(gameId);
        //
        return newlyAddedSlotDTO;
    }

    /**
     * validateSlotName
     * <p>
     * Validate's if slot name is already exists or not
     * 
     * @param existingSlots
     * @param slotId
     * @param slotName
     * @throws GameszoneException
     */
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

    @Override
    public void deleteSlot(Integer slotId) throws GameszoneException {

        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new GameszoneException("SlotService.SLOT_NOT_FOUND"));

        slotRepository.delete(slot);
    }
}
