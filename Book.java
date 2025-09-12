

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private int ano;
    private BookStatus status;
    private List<Loan> loans = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public Book() {
    }

    public Book(Long id, String titulo, String autor, String isbn, String categoria, int ano, BookStatus status) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.ano = ano;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans); // Defensive copy
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations); // Defensive copy
    }

    // Business methods
    public void addLoan(Loan loan) {
        if (loan != null && !loans.contains(loan)) {
            loans.add(loan);
            loan.setBook(this);
        }
    }

    public void removeLoan(Loan loan) {
        if (loan != null && loans.remove(loan)) {
            loan.setBook(null);
        }
    }

    public void addReservation(Reservation reservation) {
        if (reservation != null && !reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.setBook(this);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservation != null && reservations.remove(reservation)) {
            reservation.setBook(null);
        }
    }

    public boolean isAvailable() {
        return status == BookStatus.DISPONIVEL;
    }

    public void updateStatusBasedOnLoans() {
        boolean isLoaned = false;
        for (Loan loan : loans) {
            if (loan.getDataDevolucaoReal() == null) {
                isLoaned = true;
                break;
            }
        }
        this.status = isLoaned ? BookStatus.EMPRESTADO : BookStatus.DISPONIVEL;
    }
}