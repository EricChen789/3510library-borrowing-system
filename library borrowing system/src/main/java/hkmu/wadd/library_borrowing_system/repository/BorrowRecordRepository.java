package hkmu.wadd.library_borrowing_system.repository;

import hkmu.wadd.library_borrowing_system.model.BorrowRecord;
import hkmu.wadd.library_borrowing_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserAndReturnedFalse(User user);
    List<BorrowRecord> findByUser(User user);
    boolean existsByUserIdAndBookIdAndReturnedFalse(Long userId, Long bookId);
}