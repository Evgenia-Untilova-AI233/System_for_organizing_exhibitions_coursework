package ua.opnu.jenya.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.jenya.DTO.ExhibitionDTO;
import ua.opnu.jenya.Service.ExhibitionService;

import java.util.List;

@RestController
@RequestMapping("/api/exhibitions")
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @GetMapping
    public ResponseEntity<List<ExhibitionDTO>> getAllExhibitions() {
        return ResponseEntity.ok(exhibitionService.getAllExhibitions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExhibitionDTO> getExhibition(@PathVariable Long id) {
        ExhibitionDTO dto = exhibitionService.getExhibitionById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExhibitionDTO> createExhibition(@RequestBody ExhibitionDTO dto) {
        return ResponseEntity.ok(exhibitionService.createExhibition(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExhibitionDTO> updateExhibition(@PathVariable Long id, @RequestBody ExhibitionDTO dto) {
        ExhibitionDTO updated = exhibitionService.updateExhibition(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExhibition(@PathVariable Long id) {
        exhibitionService.deleteExhibition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/current")
    public ResponseEntity<List<ExhibitionDTO>> getCurrentExhibitions() {
        return ResponseEntity.ok(exhibitionService.getCurrentExhibitions());
    }
}
