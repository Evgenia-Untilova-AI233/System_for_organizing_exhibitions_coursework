package ua.opnu.jenya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.opnu.jenya.Model.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l LEFT JOIN l.exhibitions e GROUP BY l.id ORDER BY COUNT(e) DESC")
    List<Location> findTopLocationsByExhibitionCount();

}
