package hkmu.wadd.library_borrowing_system.service;

import hkmu.wadd.library_borrowing_system.LibraryConstants;
import hkmu.wadd.library_borrowing_system.model.Book;
import hkmu.wadd.library_borrowing_system.model.BorrowRecord;
import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.repository.BookRepository;
import hkmu.wadd.library_borrowing_system.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BorrowRecord borrowBook(User user, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        if (borrowRecordRepository.existsByUserIdAndBookIdAndReturnedFalse(user.getId(), bookId)) {
            throw new RuntimeException("You have already borrowed this book");
        }

        long currentBorrows = borrowRecordRepository.findByUserAndReturnedFalse(user).size();
        if (currentBorrows >= 5) {
            throw new RuntimeException("You cannot borrow more than 5 books at a time");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        LocalDateTime dueDate = LocalDateTime.now().plusDays(LibraryConstants.LOAN_PERIOD_DAYS);
        BorrowRecord record = new BorrowRecord(user, book, dueDate);
        return borrowRecordRepository.save(record);
    }

    @Transactional
    public void returnBook(Long userId, Long bookId) {
        List<BorrowRecord> records = borrowRecordRepository.findByUserAndReturnedFalse(new User() {{ setId(userId); }});

        BorrowRecord record = records.stream()
                .filter(r -> r.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.setReturned(true);
        record.setReturnDate(LocalDateTime.now());
        borrowRecordRepository.save(record);

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }

    public List<BorrowRecord> getUserBorrowHistory(User user) {
        return borrowRecordRepository.findByUser(user);
    }

    public List<BorrowRecord> getCurrentBorrows(User user) {
        return borrowRecordRepository.findByUserAndReturnedFalse(user);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public List<BorrowRecord> getOverdueBorrows() {
        return borrowRecordRepository.findAll().stream()
                .filter(record -> !record.isReturned() && record.isOverdue())
                .toList();
    }
}