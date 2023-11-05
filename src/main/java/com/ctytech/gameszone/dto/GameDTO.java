package com.ctytech.gameszone.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GameDTO {

    private Integer gameId;

    @NotNull(message = "{game.gamename.absent}")
    @Size(max = 25, min = 3, message = "{game.gamename.invalid}")
    private String gameName;

    private String image;

    private List<SlotDTO> slotDTOs;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<SlotDTO> getSlotDTOs() {
        return slotDTOs;
    }

    public void setSlotDTOs(List<SlotDTO> slotDTOs) {
        this.slotDTOs = slotDTOs;
    }

}
