package com.ctytech.gameszone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SlotDTO updateSlot(Integer gameId, Integer slotId, SlotDTO slot) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSlot'");
    }

}
