package hkmu.wadd.library_borrowing_system.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsoleLinkPrinter {

    @EventListener(ApplicationReadyEvent.class)
    public void printLinks() {
        System.out.println("\n" +
                "╔══════════════════════════════════════════════════════════════════════════╗\n" +
                "║                                                                          ║\n" +
                "║   LIBRARY BORROWING SYSTEM - STARTED SUCCESSFULLY!                       ║\n" +
                "║                                                                          ║\n" +
                "║   Application URL:                                                       ║\n" +
                "║   http://localhost:8080                                                  ║\n" +
                "║                                                                          ║\n" +
                "║   H2 Database Console:                                                   ║\n" +
                "║   http://localhost:8080/h2-console                                       ║\n" +
                "║   JDBC URL: jdbc:h2:mem:librarydb                                        ║\n" +
                "║   Username: sa                                                           ║\n" +
                "║   Password: password                                                     ║\n" +
                "║                                                                          ║\n" +
                "║   Test Accounts:                                                         ║\n" +
                "║   Admin: admin@library.com / admin123                                    ║\n" +
                "║   User: john@example.com / password123                                   ║\n" +
                "║   User: jane@example.com / password123                                   ║\n" +
                "║                                                                          ║\n" +
                "╚══════════════════════════════════════════════════════════════════════════╝\n");

        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://localhost:8080"));
                System.out.println("Browser opened automatically!\n");
            }
        } catch (Exception e) {
            System.out.println("Please manually open http://localhost:8080 in your browser\n");
        }
    }
}