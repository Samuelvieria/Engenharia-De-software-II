
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double multa;
    private User user;
    private Book book;

    public Loan() {
    }

    public Loan(Long id, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal, double multa) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.multa = multa;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
        if (this.dataDevolucaoReal != null) {
            calculateMulta();
        }
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
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
    public void calculateMulta() {
        if (dataDevolucaoReal != null && dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            long daysLate = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
            this.multa = daysLate * 1.0; // Assuming 1.0 per day fine rate
        } else {
            this.multa = 0.0;
        }
    }

    public boolean isOverdue() {
        if (dataDevolucaoReal == null) {
            return LocalDate.now().isAfter(dataDevolucaoPrevista);
        }
        return dataDevolucaoReal.isAfter(dataDevolucaoPrevista);
    }
}