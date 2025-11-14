package br.ufg.inf.es.engsoft2.biblioteca.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;
    private User user;
    private Loan loan;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        book = new Book(1L, "Java Concurrency", "Brian Goetz", "987654321", "TI", 2014);
        book.setStatus(BookStatus.DISPONIVEL);
        user = new User(1L, "2023001", "senha123", UserType.ALUNO);
        loan = new Loan(1L, LocalDate.now(), LocalDate.now().plusDays(7), null, 0.0, user, book);
        reservation = new Reservation(1L, LocalDate.now(), ReservationStatus.ATIVA, user, book);
    }

    @Test
    void deveAtualizarStatusParaEmprestadoAoAdicionarLoan() {
        book.addLoan(loan);
        assertEquals(BookStatus.EMPRESTADO, book.getStatus());
    }

    @Test
    void deveAtualizarStatusParaDisponivelAoRemoverLoan() {
        book.addLoan(loan);
        book.removeLoan(loan);
        assertEquals(BookStatus.DISPONIVEL, book.getStatus());
    }

    @Test
    void deveVerificarDisponibilidadeCorretamente() {
        assertTrue(book.isAvailable());
        book.addLoan(loan);
        assertFalse(book.isAvailable());
        book.removeLoan(loan);
        assertTrue(book.isAvailable());
    }

    @Test
    void deveAdicionarReservaApenasSeDisponivel() {
        book.addReservation(reservation);
        assertTrue(book.getReservations().contains(reservation));

        book.addLoan(loan);
        Reservation nova = new Reservation(2L, LocalDate.now(), ReservationStatus.ATIVA, user, book);
        assertThrows(IllegalStateException.class, () -> book.addReservation(nova));
    }

    @Test
    void deveRemoverReservaBidirecional() {
        book.addReservation(reservation);
        user.addReservation(reservation);
        book.removeReservation(reservation);
        assertFalse(book.getReservations().contains(reservation));
        assertNull(reservation.getBook());
        assertFalse(user.getReservations().contains(reservation));
    }
}