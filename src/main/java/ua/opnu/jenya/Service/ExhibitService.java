package ua.opnu.jenya.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.jenya.DTO.ExhibitDTO;
import ua.opnu.jenya.Model.Exhibit;
import ua.opnu.jenya.Repository.ExhibitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitService {

    private final ExhibitRepository exhibitRepository;

    public List<ExhibitDTO> getAllExhibits() {
        return exhibitRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ExhibitDTO getExhibitById(Long id) {
        return exhibitRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public ExhibitDTO createExhibit(ExhibitDTO dto) {
        Exhibit exhibit = toEntity(dto);
        return toDto(exhibitRepository.save(exhibit));
    }

    public ExhibitDTO updateExhibit(Long id, ExhibitDTO dto) {
        return exhibitRepository.findById(id).map(existing -> {
            existing.setTitle(dto.getTitle());
            existing.setDescription(dto.getDescription());
            existing.setYearCreated(dto.getYearCreated());
            existing.setType(dto.getType());
            return toDto(exhibitRepository.save(existing));
        }).orElse(null);
    }

    public void deleteExhibit(Long id) {
        exhibitRepository.deleteById(id);
    }

    public List<ExhibitDTO> getExhibitsByYear(Integer year) {
        return exhibitRepository.findByYearCreated(year)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ExhibitDTO> getExhibitsWithoutExhibition() {
        return exhibitRepository.findByEntriesIsEmpty()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ExhibitDTO toDto(Exhibit entity) {
        ExhibitDTO dto = new ExhibitDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setYearCreated(entity.getYearCreated());
        dto.setType(entity.getType());
        return dto;
    }

    private Exhibit toEntity(ExhibitDTO dto) {
        Exhibit entity = new Exhibit();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setYearCreated(dto.getYearCreated());
        entity.setType(dto.getType());
        return entity;
    }
}
