package com.ctytech.gameszone.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class BookingDTO {

    private Integer bookingId;

    @NotNull(message = "{booking.bookingdate.absent}")
    @FutureOrPresent(message = "{booking.bookingdate.past}")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bookingDate;

    private LocalDateTime transactionDate;

    @NotNull(message = "{booking.gameid.absent}")
    private Integer gameId;

    @NotNull(message = "{booking.slotid.absent}")
    private Integer slotId;

    @NotNull(message = "{booking.userid.absent}")
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

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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
    public String toString() {
        return "BookingDTO [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", transactionDate="
                + transactionDate + ", gameId=" + gameId + ", slotId=" + slotId + ", userId=" + userId + ", game="
                + game + ", slot=" + slot + ", user=" + user + "]";
    }

}
