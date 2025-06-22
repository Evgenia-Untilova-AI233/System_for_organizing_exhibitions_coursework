package ua.opnu.jenya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.jenya.Model.ExhibitionEntry;

import java.util.List;

public interface ExhibitionEntryRepository extends JpaRepository<ExhibitionEntry, Long> {
    List<ExhibitionEntry> findByExhibitionId(Long exhibitionId);
    List<ExhibitionEntry> findByArtistId(Long artistId);
}
