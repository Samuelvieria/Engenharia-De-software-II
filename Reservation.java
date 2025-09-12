

import java.time.LocalDate;

public class Reservation {
    private Long id;
    private LocalDate dataReserva;
    private ReservationStatus status;
    private User user;
    private Book book;

    public Reservation() {
    }

    public Reservation(Long id, LocalDate dataReserva, ReservationStatus status) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // Business method
    public boolean isActive() {
        return status == ReservationStatus.ATIVA;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELADA;
    }
}