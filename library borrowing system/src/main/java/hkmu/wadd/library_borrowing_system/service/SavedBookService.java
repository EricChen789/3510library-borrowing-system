package hkmu.wadd.library_borrowing_system.service;

import hkmu.wadd.library_borrowing_system.model.Book;
import hkmu.wadd.library_borrowing_system.model.SavedBook;
import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.repository.BookRepository;
import hkmu.wadd.library_borrowing_system.repository.SavedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedBookService {

    @Autowired
    private SavedBookRepository savedBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void saveBook(User user, Long bookId) {
        if (savedBookRepository.existsByUserIdAndBookId(user.getId(), bookId)) {
            throw new RuntimeException("Book already saved");
        }
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        SavedBook savedBook = new SavedBook(user, book);
        savedBookRepository.save(savedBook);
    }

    @Transactional
    public void unsaveBook(User user, Long bookId) {
        savedBookRepository.deleteByUserIdAndBookId(user.getId(), bookId);
    }

    public List<Book> getSavedBooks(User user) {
        return savedBookRepository.findByUser(user).stream()
                .map(SavedBook::getBook)
                .collect(Collectors.toList());
    }

    public boolean isBookSaved(User user, Long bookId) {
        return savedBookRepository.existsByUserIdAndBookId(user.getId(), bookId);
    }
}