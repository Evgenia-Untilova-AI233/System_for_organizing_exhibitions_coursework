package ua.opnu.jenya.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.jenya.DTO.ExhibitionEntryDTO;
import ua.opnu.jenya.Model.ExhibitionEntry;
import ua.opnu.jenya.Repository.ArtistRepository;
import ua.opnu.jenya.Repository.ExhibitionEntryRepository;
import ua.opnu.jenya.Repository.ExhibitRepository;
import ua.opnu.jenya.Repository.ExhibitionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionEntryService {

    private final ExhibitionEntryRepository entryRepository;
    private final ExhibitRepository exhibitRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ArtistRepository artistRepository;

    public List<ExhibitionEntryDTO> getAllEntries() {
        return entryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ExhibitionEntryDTO getEntryById(Long id) {
        return entryRepository.findById(id).map(this::toDto).orElse(null);
    }

    public ExhibitionEntryDTO createEntry(ExhibitionEntryDTO dto) {
        ExhibitionEntry entry = buildEntryFromDto(dto, new ExhibitionEntry());
        return toDto(entryRepository.save(entry));
    }

    public ExhibitionEntryDTO updateEntry(Long id, ExhibitionEntryDTO dto) {
        return entryRepository.findById(id).map(existing -> {
            ExhibitionEntry updated = buildEntryFromDto(dto, existing);
            return toDto(entryRepository.save(updated));
        }).orElse(null);
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

    public List<ExhibitionEntryDTO> getEntriesByExhibition(Long exhibitionId) {
        return entryRepository.findByExhibitionId(exhibitionId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ExhibitionEntryDTO> getEntriesByArtist(Long artistId) {
        return entryRepository.findByArtistId(artistId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ExhibitionEntry buildEntryFromDto(ExhibitionEntryDTO dto, ExhibitionEntry entry) {
        entry.setExhibit(exhibitRepository.findById(dto.getExhibitId()).orElse(null));
        entry.setExhibition(exhibitionRepository.findById(dto.getExhibitionId()).orElse(null));
        entry.setArtist(artistRepository.findById(dto.getArtistId()).orElse(null));
        entry.setPlacement(dto.getPlacement());
        return entry;
    }

    private ExhibitionEntryDTO toDto(ExhibitionEntry entry) {
        ExhibitionEntryDTO dto = new ExhibitionEntryDTO();
        dto.setId(entry.getId());
        dto.setExhibitId(entry.getExhibit() != null ? entry.getExhibit().getId() : null);
        dto.setExhibitionId(entry.getExhibition() != null ? entry.getExhibition().getId() : null);
        dto.setArtistId(entry.getArtist() != null ? entry.getArtist().getId() : null);
        dto.setPlacement(entry.getPlacement());
        return dto;
    }
}
