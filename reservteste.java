package br.ufg.inf.es.engsoft2.biblioteca.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = new User(1L, "2023001", "senha123", UserType.PROFESSOR);
        book = new Book(1L, "Algoritmos", "Cormen", "999888777", "Ciência", 2001);
        reservation = new Reservation(1L, LocalDate.now(), ReservationStatus.ATIVA, user, book);
    }

    @Test
    void deveCancelarReservaCorretamente() {
        reservation.cancel();
        assertEquals(ReservationStatus.CANCELADA, reservation.getStatus());
    }

    @Test
    void reservaCanceladaNaoDeveEstarAtiva() {
        reservation.cancel();
        assertFalse(reservation.isActive());
    }

    @Test
    void deveManterBidirecionalidadeAoCancelar() {
        user.addReservation(reservation);
        book.addReservation(reservation);
        reservation.cancel();
        assertFalse(user.getReservations().contains(reservation));
        assertFalse(book.getReservations().contains(reservation));
    }
}