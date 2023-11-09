package com.ctytech.gameszone.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {

    private Integer bookingId;

    private LocalDate booikingDate;

    private LocalDateTime transactionDate;

    private Integer gameId;

    private Integer slotId;

    private Integer userId;

    private GameDTO game;

    private SlotDTO slot;

    private UserDTO user;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBooikingDate() {
        return booikingDate;
    }

    public void setBooikingDate(LocalDate booikingDate) {
        this.booikingDate = booikingDate;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public SlotDTO getSlot() {
        return slot;
    }

    public void setSlot(SlotDTO slot) {
        this.slot = slot;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
        result = prime * result + ((booikingDate == null) ? 0 : booikingDate.hashCode());
        result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
        result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
        result = prime * result + ((slotId == null) ? 0 : slotId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((game == null) ? 0 : game.hashCode());
        result = prime * result + ((slot == null) ? 0 : slot.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookingDTO other = (BookingDTO) obj;
        if (bookingId == null) {
            if (other.bookingId != null)
                return false;
        } else if (!bookingId.equals(other.bookingId))
            return false;
        if (booikingDate == null) {
            if (other.booikingDate != null)
                return false;
        } else if (!booikingDate.equals(other.booikingDate))
            return false;
        if (transactionDate == null) {
            if (other.transactionDate != null)
                return false;
        } else if (!transactionDate.equals(other.transactionDate))
            return false;
        if (gameId == null) {
            if (other.gameId != null)
                return false;
        } else if (!gameId.equals(other.gameId))
            return false;
        if (slotId == null) {
            if (other.slotId != null)
                return false;
        } else if (!slotId.equals(other.slotId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (game == null) {
            if (other.game != null)
                return false;
        } else if (!game.equals(other.game))
            return false;
        if (slot == null) {
            if (other.slot != null)
                return false;
        } else if (!slot.equals(other.slot))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BookingDTO [bookingId=" + bookingId + ", booikingDate=" + booikingDate + ", transactionDate="
                + transactionDate + ", gameId=" + gameId + ", slotId=" + slotId + ", userId=" + userId + ", game="
                + game + ", slot=" + slot + ", user=" + user + "]";
    }

    

}
