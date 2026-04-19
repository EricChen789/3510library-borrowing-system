package hkmu.wadd.library_borrowing_system.controller;

import hkmu.wadd.library_borrowing_system.model.Book;
import hkmu.wadd.library_borrowing_system.model.BorrowRecord;
import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.service.BookService;
import hkmu.wadd.library_borrowing_system.service.BorrowService;
import hkmu.wadd.library_borrowing_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return "redirect:/login";
        }

        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();
        List<BorrowRecord> allBorrows = borrowService.getAllBorrowRecords();
        List<BorrowRecord> overdueBorrows = borrowService.getOverdueBorrows();

        long totalBooks = books.size();
        long totalUsers = users.size();
        long activeBorrows = allBorrows.stream().filter(r -> !r.isReturned()).count();
        long overdueCount = overdueBorrows.size();

        model.addAttribute("books", books);
        model.addAttribute("users", users);
        model.addAttribute("allBorrows", allBorrows);
        model.addAttribute("overdueBorrows", overdueBorrows);
        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("activeBorrows", activeBorrows);
        model.addAttribute("overdueCount", overdueCount);

        return "admin-dashboard";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return "redirect:/login";
        }

        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        bookService.updateBook(id, book);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/users")
    public String manageUsers(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }

    @GetMapping("/users/view/{id}")
    public String viewUser(@PathVariable Long id, HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        User user = userService.findById(id);
        List<BorrowRecord> borrowHistory = borrowService.getUserBorrowHistory(user);
        List<BorrowRecord> currentBorrows = borrowService.getCurrentBorrows(user);

        model.addAttribute("viewUser", user);
        model.addAttribute("borrowHistory", borrowHistory);
        model.addAttribute("currentBorrows", currentBorrows);
        return "admin-view-user";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        User user = userService.findById(id);
        model.addAttribute("editUser", user);
        return "admin-edit-user";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String email,
                             @RequestParam String fullName,
                             @RequestParam(required = false) String password,
                             HttpSession session) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        userService.updateUser(id, email, password, fullName);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/reset-password")
    public String resetMemberPassword(@PathVariable Long id,
                                      @RequestParam(required = false) String newPassword,
                                      @RequestParam(defaultValue = "edit") String from,
                                      HttpSession session,
                                      RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }
        try {
            String pwd = userService.adminResetMemberPassword(id, newPassword);
            redirectAttributes.addFlashAttribute("passwordResetNotice", pwd);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("passwordResetError", e.getMessage());
        }
        if ("view".equals(from)) {
            return "redirect:/admin/users/view/" + id;
        }
        return "redirect:/admin/users/edit/" + id;
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        if (admin.getId().equals(id)) {
            return "redirect:/admin/users";
        }

        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/borrows")
    public String viewAllBorrows(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        List<BorrowRecord> allBorrows = borrowService.getAllBorrowRecords();
        List<BorrowRecord> overdueBorrows = borrowService.getOverdueBorrows();

        model.addAttribute("allBorrows", allBorrows);
        model.addAttribute("overdueBorrows", overdueBorrows);
        return "admin-borrows";
    }
}