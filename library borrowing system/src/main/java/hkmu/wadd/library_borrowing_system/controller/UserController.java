package hkmu.wadd.library_borrowing_system.controller;

import hkmu.wadd.library_borrowing_system.model.BorrowRecord;
import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.service.BorrowService;
import hkmu.wadd.library_borrowing_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<BorrowRecord> currentBorrows = borrowService.getCurrentBorrows(user);
        List<BorrowRecord> history = borrowService.getUserBorrowHistory(user);

        long totalBorrowed = history.size();
        long returnedCount = history.stream().filter(BorrowRecord::isReturned).count();
        long currentCount = currentBorrows.size();
        long overdueCount = currentBorrows.stream().filter(BorrowRecord::isOverdue).count();

        model.addAttribute("user", user);
        model.addAttribute("currentBorrows", currentBorrows);
        model.addAttribute("history", history);
        model.addAttribute("totalBorrowed", totalBorrowed);
        model.addAttribute("returnedCount", returnedCount);
        model.addAttribute("currentCount", currentCount);
        model.addAttribute("overdueCount", overdueCount);

        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam(required = false) String email,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) String fullName,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        User updatedUser = userService.updateUser(user.getId(), email, password, fullName);
        session.setAttribute("user", updatedUser);
        return "redirect:/profile";
    }

    @PostMapping("/return-book")
    public String returnBook(@RequestParam Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        borrowService.returnBook(user.getId(), bookId);
        return "redirect:/profile";
    }
}