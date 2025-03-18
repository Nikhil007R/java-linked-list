class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head, tail;
    private int totalBooks;

    public Library() {
        head = tail = null;
        totalBooks = 0;
    }

    // Insert at the beginning
    public void insertAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        totalBooks++;
    }

    // Insert at the end
    public void insertAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        totalBooks++;
    }

    // Insert at a specific position
    public void insertAtPosition(String title, String author, String genre, int bookId, boolean isAvailable, int position) {
        if (position <= 0) {
            insertAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        for (int i = 1; temp != null && i < position; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            insertAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }
        newBook.next = temp.next;
        newBook.prev = temp;
        if (temp.next != null) {
            temp.next.prev = newBook;
        }
        temp.next = newBook;
        if (newBook.next == null) {
            tail = newBook;
        }
        totalBooks++;
    }

    // Remove book by Book ID
    public void removeBook(int bookId) {
        if (head == null) {
            System.out.println("No books available to remove!");
            return;
        }
        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Book not found!");
            return;
        }
        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        totalBooks--;
        System.out.println("Book '" + temp.title + "' removed successfully.");
    }

    // Search book by Title
    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + temp.title + " | Author: " + temp.author + " | Genre: " + temp.genre + " | Available: " + (temp.isAvailable ? "Yes" : "No"));
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No books found with title: " + title);
    }

    // Search book by Author
    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                System.out.println("Found: " + temp.title + " | Genre: " + temp.genre + " | Available: " + (temp.isAvailable ? "Yes" : "No"));
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No books found by author: " + author);
    }

    // Update availability status
    public void updateAvailability(int bookId, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                System.out.println("Availability status updated for book '" + temp.title + "'");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found!");
    }

    // Display all books in forward order
    public void displayBooksForward() {
        if (head == null) {
            System.out.println("No books available!");
            return;
        }
        Book temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.bookId + " | Title: " + temp.title + " | Author: " + temp.author + " | Genre: " + temp.genre + " | Available: " + (temp.isAvailable ? "Yes" : "No"));
            temp = temp.next;
        }
    }

    // Display all books in reverse order
    public void displayBooksReverse() {
        if (tail == null) {
            System.out.println("No books available!");
            return;
        }
        Book temp = tail;
        while (temp != null) {
            System.out.println("ID: " + temp.bookId + " | Title: " + temp.title + " | Author: " + temp.author + " | Genre: " + temp.genre + " | Available: " + (temp.isAvailable ? "Yes" : "No"));
            temp = temp.prev;
        }
    }

    // Count total books
    public void countTotalBooks() {
        System.out.println("Total books in the library: " + totalBooks);
    }

    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.insertAtEnd("Harry Potter", "J.K. Rowling", "Fantasy", 101, true);
        library.insertAtEnd("The Hobbit", "J.R.R. Tolkien", "Fantasy", 102, false);
        library.insertAtBeginning("1984", "George Orwell", "Dystopian", 103, true);
        library.insertAtPosition("To Kill a Mockingbird", "Harper Lee", "Fiction", 104, true, 2);

        // Display all books
        System.out.println("\nBooks List (Forward Order):");
        library.displayBooksForward();

        System.out.println("\nBooks List (Reverse Order):");
        library.displayBooksReverse();

        // Searching
        System.out.println("\nSearching for 'Harry Potter':");
        library.searchByTitle("Harry Potter");

        System.out.println("\nSearching for books by 'J.K. Rowling':");
        library.searchByAuthor("J.K. Rowling");

        // Updating availability
        System.out.println("\nUpdating availability of 'The Hobbit' to Available:");
        library.updateAvailability(102, true);
        library.displayBooksForward();

        // Removing a book
        System.out.println("\nRemoving book with ID 103:");
        library.removeBook(103);
        library.displayBooksForward();

        // Counting books
        library.countTotalBooks();
    }
}
