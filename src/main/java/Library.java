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

    private boolean validateStringParameter(String ParameterToBeValidated){
        if (ParameterToBeValidated == null || ParameterToBeValidated.isEmpty()) throw new IllegalArgumentException("\nParameter \""+ParameterToBeValidated+"\" cannot be null or empty!");

        return true;
    }

    public void addBook(Book book){
        if (book == null) throw new IllegalArgumentException("\nBook to be add cannot be null!");
        
        if (findBookById(book.getId()) == null) this.books.add(book);
        else throw new IllegalArgumentException("\nBook with Id: " + book.getId() + "already exists and cannot be added!");
    }

    public void addUser(User user){
        if (user == null) throw new IllegalArgumentException("\nUser to be add cannot be null!");

        if (findUserById(user.getId()) == null) this.users.add(user);
        else throw new IllegalArgumentException("\nUser with Id: " + user.getId() + "already exists and cannot be added!");
    }
    

    public List<Book> searchBook(String word){
        
        validateStringParameter(word);

        List<Book> result = new ArrayList<>();
        
        for (Book book: books){
            if(book.getTitle().toLowerCase().contains(word.toLowerCase()) || book.getAuthor().toLowerCase().contains(word.toLowerCase())){
                result.add(book);
            }
        }

        if (result.isEmpty()) System.out.println("\nNo results found!");

        return result;
    }

    private Book findBookById(String id){
        
        validateStringParameter(id);

        for (Book book: books){
            if (book.getId().toLowerCase().equals(id.toLowerCase().trim())) return book;
        }


        return null;
    }

    private User findUserById(String id){
        
        validateStringParameter(id);

        for (User user: users){
            if (user.getId().toLowerCase().equals(id.toLowerCase().trim())) return user;
        }

        return null;
    }

    private Loan getLoanByBookId(String bookId){
        validateStringParameter(bookId);

        for (Loan loan: loans){
            if(loan.getBook().getId().equals(bookId)) return loan;
        }

        return null;

    }

    public void registerLoan(String bookId, String userId){
        
        validateStringParameter(bookId);
        validateStringParameter(userId);

        
        Book book = findBookById(bookId);

        if (book == null) throw new IllegalArgumentException("\nBook does not exist!");

        User user = findUserById(userId);
 
        if (user == null) throw new IllegalArgumentException("\nUser does not exist!");        

        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plus(1, ChronoUnit.WEEKS);        

        Loan loan = getLoanByBookId(bookId);
        boolean isBookBeingLoaned = loan != null;
        boolean userIsLoaningThisBook = (isBookBeingLoaned && loan.getUser().getId().equals(userId));


        if(book.isAvailable() && !userIsLoaningThisBook) {
            loans.add(new Loan(book, user, loanDate, returnDate));
            book.setAvailable(false);
        }
    }

    public void returnBook(String bookId, String userId){
        
        validateStringParameter(bookId);
        validateStringParameter(userId);
        

        Book book = findBookById(bookId);
        if (book == null) throw new IllegalArgumentException("\nBook does not exist!");  
        User user = findUserById(userId);
        if (user == null) throw new IllegalArgumentException("\nUser does not exist!");  
        
         
        for (Loan loan: loans){
            if (loan.getBook().getId().equals(bookId) && loan.getUser().getId().equals(userId)) {
                    loan.setReturnDate(null);
                    book.setAvailable(true);
                    break;
                }
            }
            
            
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
            if (loan.getReturnDate() != null) activeLoans.add(loan);
        }

        return activeLoans;
    }

    public void removeBook(String bookId){
        
        validateStringParameter(bookId);

        Book book = findBookById(bookId);
        if (book == null) throw new IllegalArgumentException("\nBook does not exist!");  

        if (book.isAvailable()) books.remove(book);

    }

    public void removeUser(String userId){
        User user = findUserById(userId);
        if (user == null) throw new IllegalArgumentException("\nUser does not exist!");  

        boolean hasActiveLoans = loans.stream().anyMatch(loan -> loan.getUser().getId().equals(userId) && loan.getReturnDate() == null);

        if (hasActiveLoans) throw new IllegalArgumentException("\nUser still has active loans. Unable to remove!");

        users.remove(user);
    }

    public long getDaysBeforeReturnLoan(Loan loan){
        if (loan == null) throw new IllegalArgumentException("\nLoan cannot be null!");  

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
        return "\nLibrary:"+
        "\nBooks: " + this.books.size() +
        "\nUsers: " + this.users.size() +
        "\nLoans: " + this.loans.size();
        
    }

}