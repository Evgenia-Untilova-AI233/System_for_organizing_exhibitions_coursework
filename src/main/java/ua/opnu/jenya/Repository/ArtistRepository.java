package ua.opnu.jenya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.jenya.Model.Artist;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByCountry(String country);
}
