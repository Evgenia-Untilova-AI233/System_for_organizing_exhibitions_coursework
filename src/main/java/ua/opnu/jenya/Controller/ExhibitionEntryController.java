package ua.opnu.jenya.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.jenya.DTO.ExhibitionEntryDTO;
import ua.opnu.jenya.Service.ExhibitionEntryService;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
@RequiredArgsConstructor
public class ExhibitionEntryController {

    private final ExhibitionEntryService entryService;

    @GetMapping
    public ResponseEntity<List<ExhibitionEntryDTO>> getAllEntries() {
        return ResponseEntity.ok(entryService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExhibitionEntryDTO> getEntry(@PathVariable Long id) {
        ExhibitionEntryDTO dto = entryService.getEntryById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExhibitionEntryDTO> createEntry(@RequestBody ExhibitionEntryDTO dto) {
        return ResponseEntity.ok(entryService.createEntry(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExhibitionEntryDTO> updateEntry(@PathVariable Long id, @RequestBody ExhibitionEntryDTO dto) {
        ExhibitionEntryDTO updated = entryService.updateEntry(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-exhibition/{exhibitionId}")
    public ResponseEntity<List<ExhibitionEntryDTO>> getEntriesByExhibition(@PathVariable Long exhibitionId) {
        return ResponseEntity.ok(entryService.getEntriesByExhibition(exhibitionId));
    }

    @GetMapping("/by-artist/{artistId}")
    public ResponseEntity<List<ExhibitionEntryDTO>> getEntriesByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(entryService.getEntriesByArtist(artistId));
    }
}
