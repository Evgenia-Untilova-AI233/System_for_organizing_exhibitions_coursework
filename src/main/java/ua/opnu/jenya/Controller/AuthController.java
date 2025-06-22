package ua.opnu.jenya.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.opnu.jenya.Security.AuthTokenGenerator;
import ua.opnu.jenya.Security.ExhibitionUser;
import ua.opnu.jenya.Service.AppService;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthTokenGenerator authTokenGenerator;
    private final AppService appService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          AuthTokenGenerator authTokenGenerator,
                          AppService appService) {
        this.authenticationManager = authenticationManager;
        this.authTokenGenerator = authTokenGenerator;
        this.appService = appService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new ExhibitionUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") ExhibitionUser user, @RequestParam String role, Model model) {
        try {
            appService.registerUser(user.getUsername(), user.getPassword(), role);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/form-login-token")
    public String formLoginToken(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = authTokenGenerator.tokenGenerator(authentication);
            model.addAttribute("token", token);
            return "token-success";
        } catch (Exception e) {
            model.addAttribute("error", "Невірне ім’я користувача або пароль");
            return "login";
        }
    }

    @GetMapping("/success")
    public String success(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }
}
