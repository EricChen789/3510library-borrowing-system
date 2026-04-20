package hkmu.wadd.library_borrowing_system.service;

import hkmu.wadd.library_borrowing_system.model.User;
import hkmu.wadd.library_borrowing_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String TEMP_PASSWORD_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private UserRepository userRepository;

    public User register(String email, String password, String fullName) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User(email, password, fullName);
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt.get();
        }
        throw new RuntimeException("Invalid email or password");
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, String email, String password, String fullName) {
        User user = findById(id);
        if (email != null && !email.isEmpty()) user.setEmail(email);
        if (password != null && !password.isEmpty()) user.setPassword(password);
        if (fullName != null && !fullName.isEmpty()) user.setFullName(fullName);
        return userRepository.save(user);
    }

    /**
     * Self-service password reset (demo: returns the new password to display once).
     */
    public String resetPasswordByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email.trim());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("No account found for this email");
        }
        User user = userOpt.get();
        if (user.isAdmin()) {
            throw new RuntimeException("Administrator accounts cannot use self-service reset. Please contact IT.");
        }
        String newPassword = generateTemporaryPassword();
        user.setPassword(newPassword);
        userRepository.save(user);
        return newPassword;
    }

    /**
     * Admin resets a member password. If newPassword is blank, a random password is generated.
     */
    public String adminResetMemberPassword(Long userId, String newPassword) {
        User user = findById(userId);
        if (user.isAdmin()) {
            throw new RuntimeException("Cannot reset administrator password with this action");
        }
        String pwd = (newPassword == null || newPassword.isBlank())
                ? generateTemporaryPassword()
                : newPassword.trim();
        user.setPassword(pwd);
        userRepository.save(user);
        return pwd;
    }

    private static String generateTemporaryPassword() {
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(TEMP_PASSWORD_CHARS.charAt(RANDOM.nextInt(TEMP_PASSWORD_CHARS.length())));
        }
        return sb.toString();
    }
}