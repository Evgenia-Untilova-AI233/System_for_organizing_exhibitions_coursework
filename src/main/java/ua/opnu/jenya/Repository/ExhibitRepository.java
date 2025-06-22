package ua.opnu.jenya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.jenya.Model.Exhibit;

import java.util.List;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
    List<Exhibit> findByYearCreated(Integer yearCreated);
    List<Exhibit> findByEntriesIsEmpty();
}
