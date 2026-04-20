package hkmu.wadd.library_borrowing_system.config;

import hkmu.wadd.library_borrowing_system.LibraryConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("requestUri")
    public String requestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("loanPeriodDays")
    public int loanPeriodDays() {
        return LibraryConstants.LOAN_PERIOD_DAYS;
    }
}
