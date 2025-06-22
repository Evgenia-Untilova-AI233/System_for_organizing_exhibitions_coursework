package ua.opnu.jenya.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.jenya.DTO.ExhibitionDTO;
import ua.opnu.jenya.Model.Exhibition;
import ua.opnu.jenya.Repository.ExhibitionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    public List<ExhibitionDTO> getAllExhibitions() {
        return exhibitionRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ExhibitionDTO getExhibitionById(Long id) {
        return exhibitionRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public ExhibitionDTO createExhibition(ExhibitionDTO dto) {
        Exhibition exhibition = toEntity(dto);
        return toDto(exhibitionRepository.save(exhibition));
    }

    public ExhibitionDTO updateExhibition(Long id, ExhibitionDTO dto) {
        return exhibitionRepository.findById(id).map(existing -> {
            existing.setTitle(dto.getTitle());
            existing.setStartDate(dto.getStartDate());
            existing.setEndDate(dto.getEndDate());
            return toDto(exhibitionRepository.save(existing));
        }).orElse(null);
    }

    public void deleteExhibition(Long id) {
        exhibitionRepository.deleteById(id);
    }

    public List<ExhibitionDTO> getCurrentExhibitions() {
        LocalDate now = LocalDate.now();
        return exhibitionRepository.findByStartDateBeforeAndEndDateAfter(now, now)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Mapper methods
    private ExhibitionDTO toDto(Exhibition entity) {
        ExhibitionDTO dto = new ExhibitionDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        return dto;
    }

    private Exhibition toEntity(ExhibitionDTO dto) {
        Exhibition entity = new Exhibition();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        return entity;
    }
}
