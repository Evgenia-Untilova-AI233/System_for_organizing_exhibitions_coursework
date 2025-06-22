package ua.opnu.jenya.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.opnu.jenya.Repository.UserRepository;
import ua.opnu.jenya.Security.ExhibitionUser;

@Service
@RequiredArgsConstructor
public class AppService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        ExhibitionUser user = new ExhibitionUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_" + role.toUpperCase());
        userRepository.save(user);
    }
}
