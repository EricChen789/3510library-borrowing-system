package hkmu.wadd.library_borrowing_system.config;

import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.repository.UserRepository;
import hkmu.wadd.library_borrowing_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        bookService.initializeBooks();

        if (userRepository.count() == 0) {
            User admin = new User("admin@library.com", "admin123", "Administrator");
            admin.setAdmin(true);
            userRepository.save(admin);

            User user1 = new User("john@example.com", "password123", "John Doe");
            userRepository.save(user1);

            User user2 = new User("jane@example.com", "password123", "Jane Smith");
            userRepository.save(user2);

            System.out.println("Default users created!");
        }
    }
}