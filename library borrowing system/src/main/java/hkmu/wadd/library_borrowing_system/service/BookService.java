package hkmu.wadd.library_borrowing_system.service;

import hkmu.wadd.library_borrowing_system.model.Book;
import hkmu.wadd.library_borrowing_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private static final String OL = "https://covers.openlibrary.org/b/isbn/";

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllBooks();
        }
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setDescription(bookDetails.getDescription());
        book.setImageUrl(bookDetails.getImageUrl());
        book.setTotalCopies(bookDetails.getTotalCopies());
        book.setAvailableCopies(bookDetails.getAvailableCopies());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * 确保馆藏至少包含20 本国际名著；已存在的书名（忽略大小写）则跳过。
     * 每种3–6 册。
     */
    public void initializeBooks() {
        Object[][] seed = new Object[][]{
                {"The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 4},
                {"Pride and Prejudice", "Jane Austen", "9780141439518", 5},
                {"War and Peace", "Leo Tolstoy", "9780140447934", 3},
                {"Moby-Dick", "Herman Melville", "9780142437247", 6},
                {"Crime and Punishment", "Fyodor Dostoevsky", "9780143058144", 4},
                {"Les Misérables", "Victor Hugo", "9780451521575", 5},
                {"Jane Eyre", "Charlotte Brontë", "9780141441146", 3},
                {"Wuthering Heights", "Emily Brontë", "9781853260018", 5},
                {"Don Quixote", "Miguel de Cervantes", "9780142437239", 6},
                {"The Odyssey", "Homer", "9780140268867", 4},
                {"The Iliad", "Homer", "9780140275363", 3},
                {"Anna Karenina", "Leo Tolstoy", "9780143035008", 5},
                {"The Brothers Karamazov", "Fyodor Dostoevsky", "9780374528379", 4},
                {"Madame Bovary", "Gustave Flaubert", "9780140449129", 6},
                {"One Hundred Years of Solitude", "Gabriel García Márquez", "9780060883287", 3},
                {"The Old Man and the Sea", "Ernest Hemingway", "9780684801223", 5},
                {"The Picture of Dorian Gray", "Oscar Wilde", "9780141439570", 4},
                {"Frankenstein", "Mary Shelley", "9780141439471", 5},
                {"Dracula", "Bram Stoker", "9780141439846", 3},
                {"The Count of Monte Cristo", "Alexandre Dumas", "9780140449264", 6}
        };

        for (Object[] row : seed) {
            String title = (String) row[0];
            if (bookRepository.existsByTitleIgnoreCase(title)) {
                continue;
            }
            String author = (String) row[1];
            String isbn = (String) row[2];
            int copies = (Integer) row[3];
            Book book = new Book(title, author, isbn, copies);
            book.setAvailableCopies(copies);
            book.setTotalCopies(copies);
            book.setImageUrl(OL + isbn + "-M.jpg");
            book.setDescription("World classic — " + title);
            bookRepository.save(book);
        }
    }

}
