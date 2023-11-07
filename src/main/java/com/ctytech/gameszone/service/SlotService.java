package com.ctytech.gameszone.service;

import java.util.List;

import com.ctytech.gameszone.dto.SlotDTO;
import com.ctytech.gameszone.exception.GameszoneException;

public interface SlotService {
    List<SlotDTO> getSlotsByGameId(Integer gameId) throws GameszoneException;
}
