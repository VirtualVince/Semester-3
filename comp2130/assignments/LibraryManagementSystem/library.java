class Library {
    private Book[] books;
    private int maxBooks;
    private int currentCount;

    public Library(int maxBooks) {
        this.maxBooks = maxBooks;
        this.books = new Book[maxBooks];
        this.currentCount = 0;
    }

    // Add a book to the library
    public void addBook(Book book) {
        if (currentCount < maxBooks) {
            books[currentCount++] = book;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    // Borrow a book using ISBN
    public void borrowBook(String ISBN) {
        for (int i = 0; i < currentCount; i++) {
            if (books[i].getISBN().equals(ISBN)) {
                books[i].borrowBook();
                return;
            }
        }
        System.out.println("Book with ISBN " + ISBN + " not found.");
    }

    // Return a book using ISBN
    public void returnBook(String ISBN) {
        for (int i = 0; i < currentCount; i++) {
            if (books[i].getISBN().equals(ISBN)) {
                books[i].returnBook();
                return;
            }
        }
        System.out.println("Book with ISBN " + ISBN + " not found.");
    }

    // Display all books in the library
    public void displayAllBooks() {
        if (currentCount == 0) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < currentCount; i++) {
                books[i].displayInfo();
                System.out.println("--------------------");
            }
        }
    }

    // Display books borrowed in the last 7 days
    public void displayRecentBorrowedBooks() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        boolean found = false;

        for (int i = 0; i < currentCount; i++) {
            if (books[i].isBorrowed() && books[i].getBorrowDate().isAfter(sevenDaysAgo)) {
                books[i].displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books borrowed in the last 7 days.");
        }
    }

    // String representation of the Library object
    @Override
    public String toString() {
        return "Library has " + currentCount + " out of " + maxBooks + " books.";
    }
}
