package ua.opnu.jenya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.jenya.Security.ExhibitionUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ExhibitionUser, Long> {
    Optional<ExhibitionUser> findByUsername(String username);
}
