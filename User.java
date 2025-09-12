

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String matricula;
    private String senha; // Criptografada
    private UserType tipo;
    private List<Loan> loans = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public User() {
    }

    public User(Long id, String matricula, String senha, UserType tipo) {
        this.id = id;
        this.matricula = matricula;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserType getTipo() {
        return tipo;
    }

    public void setTipo(UserType tipo) {
        this.tipo = tipo;
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
            loan.setUser(this);
        }
    }

    public void removeLoan(Loan loan) {
        if (loan != null && loans.remove(loan)) {
            loan.setUser(null);
        }
    }

    public void addReservation(Reservation reservation) {
        if (reservation != null && !reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.setUser(this);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservation != null && reservations.remove(reservation)) {
            reservation.setUser(null);
        }
    }

    public List<Loan> listActiveLoans() {
        List<Loan> activeLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getDataDevolucaoReal() == null) {
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }
}