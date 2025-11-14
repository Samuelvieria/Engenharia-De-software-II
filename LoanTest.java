package br.ufg.inf.es.engsoft2.biblioteca.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Loan loan;
    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = new User(1L, "2023001", "senha123", UserType.ALUNO);
        book = new Book(1L, "Design Patterns", "GoF", "111222333", "TI", 1994);
        loan = new Loan(1L, LocalDate.now().minusDays(10), LocalDate.now().minusDays(3), null, 0.0, user, book);
    }

    @Test
    void deveCalcularMultaCorretamenteComAtraso() {
        LocalDate devolucao = LocalDate.now().minusDays(3).plusDays(5); // 5 dias de atraso
        loan.setDataDevolucaoReal(devolucao);
        loan.calcularMulta();
        assertEquals(5.0, loan.getMulta(), 0.001);
    }

    @Test
    void naoDeveAplicarMultaSeDevolvidoNoPrazo() {
        loan.setDataDevolucaoReal(LocalDate.now().minusDays(3)); // no prazo
        loan.calcularMulta();
        assertEquals(0.0, loan.getMulta(), 0.001);
    }

    @Test
    void deveIdentificarEmprestimoAtrasado() {
        assertTrue(loan.isOverdue());
        loan.setDataDevolucaoReal(LocalDate.now().minusDays(5));
        assertFalse(loan.isOverdue());
    }

    @Test
    void devePermitirDevolucaoApenasUmaVez() {
        loan.setDataDevolucaoReal(LocalDate.now().minusDays(3));
        assertThrows(IllegalStateException.class, () ->
                loan.setDataDevolucaoReal(LocalDate.now()));
    }
}