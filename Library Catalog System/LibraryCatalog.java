import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Book {
    private String bookId;
    private String bookTitle;
    private String author;
    private String status;

    public Book(String bookId, String bookTitle, String author, String status) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", author=" + author + ", status=" + status
                + "]";
    }
}

public class LibraryCatalog {
    Set<Book> books = new HashSet<>();
    Scanner sc = new Scanner(System.in);
    Validator vd = new Validator();

    public void addBook() {
        String id = vd.validateId();
        String bookName = vd.validateAuthorTitle("Book name");
        String author = vd.validateAuthorTitle("Author");

        if (books.stream().anyMatch(book -> book.getBookId().equals(id))) {
            System.out.println("Duplicate id");
        } else {
            Book newBook = new Book(id, bookName, author, "Available");
            books.add(newBook);
            System.out.println("Book added successfully: " + newBook);
        }
    }

    public void searchBook(String bookName) {
        boolean found = false;
        for (Book book : books) {
            if (book.getBookTitle().equalsIgnoreCase(bookName) || book.getAuthor().equalsIgnoreCase(bookName)) {
                System.out.println(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not available");
        }
    }

    public void displayBooks() {
        System.out.println("BookId\tBookTitle\tAuthor\tStatus");

        for (Book book : books) {
            System.out.println(book.getBookId() + "\t" + book.getBookTitle() + "\t" + book.getAuthor() + "\t" + book.getStatus());
        }
    }

    public void checkoutBook() {
        System.out.println("Enter the book name: ");
        String bookName = sc.next();
        boolean found = false;

        for (Book book : books) {
            if (book.getBookTitle().equalsIgnoreCase(bookName) && book.getStatus().equalsIgnoreCase("Available")) {
                System.out.println("Book checked out successfully!!");
                book.setStatus("Not Available");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("This book is not available to check out");
        }
    }

    public void returnBook() {
        String bookId = vd.validateId();
        boolean found = false;

        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId) && book.getStatus().equalsIgnoreCase("Not Available")) {
                System.out.println("Book returned successfully!!");
                book.setStatus("Available");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Cannot return the book. Either the book is not found or it is already available.");
        }
    }

    public static void main(String[] args) {
        LibraryCatalog libraryCatalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display Books");
            System.out.println("4. Checkout Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    libraryCatalog.addBook();
                    break;
                case 2:
                    System.out.print("Enter book name or author to search: ");
                    String searchInput = scanner.next();
                    libraryCatalog.searchBook(searchInput);
                    break;
                case 3:
                    libraryCatalog.displayBooks();
                    break;
                case 4:
                    libraryCatalog.checkoutBook();
                    break;
                case 5:
                    libraryCatalog.returnBook();
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

class Validator {
    Scanner sc = new Scanner(System.in);

    public String validateId() {
        System.out.println("Enter book ID: ");
        return sc.next();
    }

    public String validateAuthorTitle(String prompt) {
        System.out.println("Enter " + prompt + ": ");
        return sc.next();
    }
}
