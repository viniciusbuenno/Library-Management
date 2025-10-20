import java.time.LocalDate;
import java.util.Objects;


public class Loan implements Comparable<Loan>{
    private final Book book;
    private final User user;
    private final LocalDate loanDate;
    private LocalDate returnDate;

    public Book getBook(){ return this.book; }
    public User getUser(){ return this.user; }
    public LocalDate getLoanDate(){ return this.loanDate; }
    public LocalDate getReturnDate(){ return this.returnDate; }

    public void setReturnDate(LocalDate returnDate){
        if(returnDate == null || returnDate.isBefore(this.loanDate)) throw new IllegalArgumentException("Loan returnal date cannot be eager than the loan date or empty");
        this.returnDate = returnDate;
    }

    public Loan(Book book, User user, LocalDate loanDate, LocalDate returnDate){
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Loan(Book book, User user, LocalDate loanDate){
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.returnDate = loanDate.plusDays(7l);
    }

    @Override
    public String toString(){
        return this.book.toString() + this.user.toString() + "\nLoan Date: " + this.loanDate + "\nReturn Date: " + this.returnDate;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || other.getClass() != this.getClass()) return false;
        
        Loan otherLoan = (Loan) other;

        return this.book.equals(otherLoan.getBook()) && this.user.equals(otherLoan.getUser()) && this.loanDate.isEqual(otherLoan.getLoanDate()) && this.returnDate.isEqual(otherLoan.getReturnDate());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.book, this.user, this.loanDate, this.returnDate);
    }

    @Override
    public int compareTo(Loan otherLoan){
        int loanDateCompareToOtherLoanLoanDate = this.loanDate.compareTo(otherLoan.getLoanDate());
        if (loanDateCompareToOtherLoanLoanDate != 0) return loanDateCompareToOtherLoanLoanDate;
        
        if (this.returnDate == null && otherLoan.getReturnDate() == null) return 0;
        if (this.returnDate == null) return -1;
        if (otherLoan.getReturnDate() == null) return 1;

        
        int returnDateCompareToOtherLoanReturnDate = this.returnDate.compareTo(otherLoan.returnDate);
        if (returnDateCompareToOtherLoanReturnDate != 0) return returnDateCompareToOtherLoanReturnDate;
        
        return 0;
    }

}