package hkmu.wadd.library_borrowing_system.controller;

import hkmu.wadd.library_borrowing_system.model.Book;
import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.service.BookService;
import hkmu.wadd.library_borrowing_system.service.BorrowService;
import hkmu.wadd.library_borrowing_system.service.SavedBookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private SavedBookService savedBookService;

    @GetMapping("/search")
    public String showSearchPage(@RequestParam(required = false) String keyword,
                                 HttpSession session,
                                 Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", user);
        return "search";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long bookId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            borrowService.borrowBook(user, bookId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/search";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            savedBookService.saveBook(user, bookId);
        } catch (Exception e) {
        }
        return "redirect:/search";
    }
}