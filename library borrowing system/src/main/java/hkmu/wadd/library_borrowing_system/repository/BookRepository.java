package hkmu.wadd.library_borrowing_system.repository;

import hkmu.wadd.library_borrowing_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

    boolean existsByTitleIgnoreCase(String title);
}