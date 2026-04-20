package hkmu.wadd.library_borrowing_system.repository;

import hkmu.wadd.library_borrowing_system.model.SavedBook;
import hkmu.wadd.library_borrowing_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SavedBookRepository extends JpaRepository<SavedBook, Long> {
    List<SavedBook> findByUser(User user);
    Optional<SavedBook> findByUserIdAndBookId(Long userId, Long bookId);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
    boolean existsByUserIdAndBookId(Long userId, Long bookId);
}