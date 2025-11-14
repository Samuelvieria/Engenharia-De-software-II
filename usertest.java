package br.ufg.inf.es.engsoft2.biblioteca.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Book book;
    private Loan loan;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        user = new User(1L, "2023001", "senha123", UserType.ALUNO);
        book = new Book(1L, "Clean Code", "Robert Martin", "123456789", "Programação", 2008);
        loan = new Loan(1L, LocalDate.now(), LocalDate.now().plusDays(7), null, 0.0, user, book);
        reservation = new Reservation(1L, LocalDate.now(), ReservationStatus.ATIVA, user, book);
    }

    @Test
    void deveAdicionarEmprestimoBidirecional() {
        user.addLoan(loan);
        assertTrue(user.getLoans().contains(loan));
        assertEquals(user, loan.getUser());
        assertTrue(book.getLoans().contains(loan));
    }

    @Test
    void deveRemoverEmprestimoBidirecional() {
        user.addLoan(loan);
        book.addLoan(loan);
        user.removeLoan(loan);
        assertFalse(user.getLoans().contains(loan));
        assertNull(loan.getUser());
        assertFalse(book.getLoans().contains(loan));
    }

    @Test
    void deveListarApenasEmprestimosAtivos() {
        Loan loanDevolvido = new Loan(2L, LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(3), LocalDate.now().minusDays(1), 0.0, user, book);
        user.addLoan(loan);
        user.addLoan(loanDevolvido);
        List<Loan> ativos = user.getActiveLoans();
        assertEquals(1, ativos.size());
        assertTrue(ativos.contains(loan));
    }

    @Test
    void deveAdicionarReservaBidirecional() {
        user.addReservation(reservation);
        assertTrue(user.getReservations().contains(reservation));
        assertEquals(user, reservation.getUser());
        assertTrue(book.getReservations().contains(reservation));
    }
}