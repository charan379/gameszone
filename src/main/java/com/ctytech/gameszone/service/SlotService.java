package com.ctytech.gameszone.service;

import java.util.List;

import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.exception.GameszoneException;

public interface SlotService {
    /**
     * @param gameId
     * @return List<SlotDTO>
     * @see SlotDTO
     * @throws GameszoneException
     */
    List<SlotDTO> getSlotsByGameId(Integer gameId) throws GameszoneException;

    /**
     * @param gameId
     * @param slotId
     * @param update
     * @return SlotDTO
     * @see SlotDTO
     * @throws GameszoneException
     */
    SlotDTO updateSlot(Integer gameId, Integer slotId, SlotDTO update) throws GameszoneException;

    /**
     * @param gameId
     * @param newSlot
     * @return SlotDTO
     * @see SlotDTO
     * @throws GameszoneException
     */
    SlotDTO addSlot(Integer gameId, SlotDTO newSlot) throws GameszoneException;

    void deleteSlot(Integer slotId) throws GameszoneException;
}
