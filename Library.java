import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library{
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;

    public Library(){
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addBook(Book book){
        //validation
        if (findBookById(book.getId()) == null) this.books.add(book);
        
    }

    public void addUser(User user){
        //validation
        if (findUserById(user.getId()) == null) this.users.add(user);
    }

    public List<Book> searchBook(String word){
        List<Book> result = new ArrayList<>();
        //validation
        for (Book book: books){
            if(book.getTitle().toLowerCase().contains(word.toLowerCase()) || book.getAuthor().toLowerCase().contains(word.toLowerCase())){
                result.add(book);
            }
        }

        return result;
    }

    private Book findBookById(String id){
        //validation
        for (Book book: books){
            if (book.getId().toLowerCase().equals(id.toLowerCase().trim())) return book;
        }

        return null;
    }

    private User findUserById(String id){
        
        //validation
        for (User user: users){
            if (user.getId().toLowerCase().equals(id.toLowerCase().trim())) return user;
        }

        return null;
    }

    public void registerLoan(String bookId, String userId){
        //validation

        Book book = findBookById(bookId);
        User user = findUserById(userId);

        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plus(1, ChronoUnit.WEEKS);
        
        loans.add(new Loan(book, user, loanDate, returnDate));
        book.setAvailable(false);
        
    }

    public void returnBook(String bookId, String userId){
        //validation
        User user = findUserById(userId);
        Book book = findBookById(bookId);
        
        for (Loan loan: loans){
            if (loan.getBook().getId().equals(bookId) && loan.getUser().getId().equals(userId)) loan.setReturnDate(null);
        }
        
        book.setAvailable(true);
        loans.removeIf(loan -> loan.getBook().equals(book) && loan.getReturnDate() == null);
    }

    public List<Book> listAvailableBooks(){
        List<Book> availableBooks = new ArrayList<>();
        for (Book book: books){
            if (book.isAvailable()) availableBooks.add(book);
        }

        return availableBooks;

    }

    public List<Book> listAllBooks(){
        return books;
    }


    public List<Loan> listActiveLoans(){
        List<Loan> activeLoans = new ArrayList<>();

        for (Loan loan: loans){
            if (loan.getReturnDate() != null) loans.add(loan);
        }

        return activeLoans;
    }

    public void removeBook(String bookId){
        
        //validations
        
        Book book = findBookById(bookId);

        if (!book.isAvailable()) books.remove(book);

    }

    public void removeUser(String userId){
        User user = findUserById(userId);

        boolean hasActiveLoans = loans.stream().anyMatch(loan -> loan.getUser().equals(userId) && loan.getReturnDate() == null);

        if (hasActiveLoans) throw new IllegalArgumentException("User still has active loans. Unable to remove!");

        users.remove(user);
    }

    public long getDaysBeforeReturnLoan(Loan loan){
        if (loan.getReturnDate() == null) return 0;

        return ChronoUnit.DAYS.between(LocalDate.now(), loan.getReturnDate());
    }

    public List<Loan> getLoansExpiringSoon(){
        List<Loan> expiringLoans = new ArrayList<>();

        for (Loan loan: loans){
            if (loan.getReturnDate() != null && ChronoUnit.DAYS.between(LocalDate.now(), loan.getReturnDate()) < 4) expiringLoans.add(loan);
        }

        return expiringLoans;

    }



    @Override
    public String toString(){
        return "Library:"+
        "\nBooks: " + this.books.size() +
        "\nUsers: " + this.users.size() +
        "\nLoans: " + this.loans.size();
        
    }

}