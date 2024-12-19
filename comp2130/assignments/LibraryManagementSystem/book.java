import java.time.LocalDate;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isBorrowed;
    private LocalDate borrowDate;

    // Constructor
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false;
        this.borrowDate = null;
    }

    // Borrow the book
    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            borrowDate = LocalDate.now();
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    // Return the book
    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            borrowDate = null;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book was not borrowed.");
        }
    }

    // Display book details
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Is Borrowed: " + (isBorrowed ? "Yes" : "No"));
        if (isBorrowed) {
            System.out.println("Borrow Date: " + borrowDate);
        }
    }

    // String representation of the Book object
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN +
                ", Is Borrowed: " + isBorrowed + (isBorrowed ? ", Borrow Date: " + borrowDate : "");
    }

    // Getter methods
    public String getISBN() {
        return ISBN;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
