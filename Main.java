import java.util.Scanner;

public class Main{
    
    
    private static void showMainMenu(){
        System.out.println("Library Management System\n"+
                "NAVIGATE\n"+
                "1. Users"+
                "2. Books"+
                "3. Loans");
}


    private static void showUsersMenu(){
        System.out.println("USERS\n"+
        "1. Add New User"+
        "2. Remove Existing User"+
        "3. Search Existing User (Name Search)"+
        "4. List Existing Users");
    }
    
    private static void showBooksMenu(){
        System.out.println("BOOKS\n"+
        "1. Add New Book"+
        "2. Remove Existing Book"+
        "3. Search Books (Title Search)"+
        "4. Change Book Availability"+
        "5. List Books");
    }

    private static void showBookListingMethods(){
        System.out.println("BOOK LISTING OPTIONS\n"+
        "1. List All Existing Books"+
        "2. List Available Books Only");
    }

    private static void showLoansMenu(){
        System.out.println("LOANS\n"+
        "1. Loan a Book"+
        "2. Return a Book"+
        "3. List Loans");
    }

    private static void showLoanListingMethods(){
        System.out.println("LOAN LISTING OPTIONS\n"+
        "1. List All Active Loans"+
        "2. List Expiring Loans Only");
    }

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        
        Library librarySystem = new Library();
        
        
        
        
        byte choice = 100;
        while(choice != 0){
            showMainMenu();

            choice = reader.nextByte();

            switch(choice){
                case 1: // showUsersMenu()
                break;

                case 2: // showBooksMenu()
                break;

                case 3: // showLoansMenu()
                break;
            }
            
        }
        
        reader.close();

    }
}