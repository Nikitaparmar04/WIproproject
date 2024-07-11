// LibraryManagementSystem.java

import java.util.ArrayList;
import java.util.List;

// Interface for Library Operations
interface LibraryOperations {
    void addBook(Book book);

    void borrowBook(String bookTitle, String borrowerName);

    void returnBook(String bookTitle);

    void displayBooks();
}

// Book class
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Borrowed: " + (isBorrowed ? "Yes" : "No");
    }
}

// Library class
class Library implements LibraryOperations {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    @Override
    public void borrowBook(String bookTitle, String borrowerName) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && !book.isBorrowed()) {
                book.borrow();
                System.out.println(borrowerName + " borrowed " + bookTitle);
                return;
            }
        }
        System.out.println("Sorry, the book is not available.");
    }

    @Override
    public void returnBook(String bookTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && book.isBorrowed()) {
                book.returnBook();
                System.out.println("Book returned: " + bookTitle);
                return;
            }
        }
        System.out.println("Sorry, the book was not found.");
    }

    @Override
    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));

        // Display all books
        library.displayBooks();

        // Borrowing books
        library.borrowBook("1984", "Alice");
        library.borrowBook("The Great Gatsby", "Bob");

        // Trying to borrow an already borrowed book
        library.borrowBook("1984", "Charlie");

        // Display all books after borrowing
        library.displayBooks();

        // Returning books
        library.returnBook("1984");

        // Display all books after returning
        library.displayBooks();
    }
}
