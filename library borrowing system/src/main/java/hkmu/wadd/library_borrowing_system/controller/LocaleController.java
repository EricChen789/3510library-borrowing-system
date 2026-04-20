package hkmu.wadd.library_borrowing_system.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

@Controller
public class LocaleController {

    @GetMapping("/locale")
    public String changeLocale(@RequestParam String lang,
                               @RequestParam String to,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if (to == null || !to.startsWith("/") || to.startsWith("//")) {
            to = "/login";
        }
        Locale locale = resolveLocale(lang);
        LocaleResolver resolver = RequestContextUtils.getLocaleResolver(request);
        if (resolver != null) {
            resolver.setLocale(request, response, locale);
        }
        return "redirect:" + to;
    }

    private static Locale resolveLocale(String lang) {
        if ("zh_CN".equalsIgnoreCase(lang) || "zh".equalsIgnoreCase(lang)) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        return Locale.ENGLISH;
    }
}
