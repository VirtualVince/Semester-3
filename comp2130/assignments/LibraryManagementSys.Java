import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize library with max 100 books
        Library library = new Library(100); 

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Display Books Borrowed in the Last 7 Days");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String ISBN = scanner.nextLine();
                    library.addBook(new Book(title, author, ISBN));
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowISBN = scanner.nextLine();
                    library.borrowBook(borrowISBN);
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnISBN = scanner.nextLine();
                    library.returnBook(returnISBN);
                    break;
                case 4:
                    library.displayAllBooks();
                    break;
                case 5:
                    library.displayRecentBorrowedBooks();
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
