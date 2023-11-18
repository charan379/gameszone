package com.ctytech.gameszone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ctytech.gameszone.dto.BookingDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @NotNull
    private LocalDate forDate;

    @NotNull
    private LocalDateTime transactionDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "slotId")
    private Slot slot;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getForDate() {
        return forDate;
    }

    public void setForDate(LocalDate bookingDate) {
        this.forDate = bookingDate;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingDTO tDto() {

        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setBookingId(bookingId);
        bookingDTO.setForDate(forDate);
        bookingDTO.setTransactionDate(transactionDate);
        bookingDTO.setGameId(game.getGameId());
        bookingDTO.setSlotId(slot.getSlotId());
        bookingDTO.setUserId(user.getUserId());
        bookingDTO.setGame(game.toDto());
        bookingDTO.setSlot(slot.toDto());
        bookingDTO.setUser(user.toDto());

        return bookingDTO;

    }
}
