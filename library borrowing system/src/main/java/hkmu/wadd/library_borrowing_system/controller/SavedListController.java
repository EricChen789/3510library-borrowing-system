package hkmu.wadd.library_borrowing_system.controller;

import hkmu.wadd.library_borrowing_system.model.Book;
import hkmu.wadd.library_borrowing_system.model.User;
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
public class SavedListController {

    @Autowired
    private SavedBookService savedBookService;

    @GetMapping("/saved-list")
    public String showSavedList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Book> savedBooks = savedBookService.getSavedBooks(user);
        model.addAttribute("savedBooks", savedBooks);
        model.addAttribute("user", user);
        return "saved-list";
    }

    @PostMapping("/unsave")
    public String unsaveBook(@RequestParam Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        savedBookService.unsaveBook(user, bookId);
        return "redirect:/saved-list";
    }
}