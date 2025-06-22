package ua.opnu.jenya.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.jenya.DTO.ExhibitDTO;
import ua.opnu.jenya.Service.ExhibitService;

import java.util.List;

@RestController
@RequestMapping("/api/exhibits")
@RequiredArgsConstructor
public class ExhibitController {

    private final ExhibitService exhibitService;

    @GetMapping
    public ResponseEntity<List<ExhibitDTO>> getAllExhibits() {
        return ResponseEntity.ok(exhibitService.getAllExhibits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExhibitDTO> getExhibit(@PathVariable Long id) {
        ExhibitDTO dto = exhibitService.getExhibitById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExhibitDTO> createExhibit(@RequestBody ExhibitDTO dto) {
        return ResponseEntity.ok(exhibitService.createExhibit(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExhibitDTO> updateExhibit(@PathVariable Long id, @RequestBody ExhibitDTO dto) {
        ExhibitDTO updated = exhibitService.updateExhibit(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExhibit(@PathVariable Long id) {
        exhibitService.deleteExhibit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<ExhibitDTO>> getByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(exhibitService.getExhibitsByYear(year));
    }

    @GetMapping("/without-exhibition")
    public ResponseEntity<List<ExhibitDTO>> getWithoutExhibition() {
        return ResponseEntity.ok(exhibitService.getExhibitsWithoutExhibition());
    }
}
