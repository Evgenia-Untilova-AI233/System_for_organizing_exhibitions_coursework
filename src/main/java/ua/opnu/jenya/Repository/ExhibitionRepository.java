package ua.opnu.jenya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.jenya.Model.Exhibition;

import java.time.LocalDate;
import java.util.List;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    List<Exhibition> findByStartDateBeforeAndEndDateAfter(LocalDate now1, LocalDate now2);
}
