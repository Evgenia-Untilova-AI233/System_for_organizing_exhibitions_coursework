package ua.opnu.jenya.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.jenya.DTO.ArtistDTO;
import ua.opnu.jenya.Model.Artist;
import ua.opnu.jenya.Repository.ArtistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ArtistDTO getArtistById(Long id) {
        return artistRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public ArtistDTO createArtist(ArtistDTO dto) {
        Artist artist = toEntity(dto);
        return toDto(artistRepository.save(artist));
    }

    public ArtistDTO updateArtist(Long id, ArtistDTO dto) {
        return artistRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setBio(dto.getBio());
            return toDto(artistRepository.save(existing));
        }).orElse(null);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    public List<ArtistDTO> getArtistsByCountry(String country) {
        return artistRepository.findByCountry(country)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ArtistDTO toDto(Artist entity) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBio(entity.getBio());
        dto.setCountry(entity.getCountry());
        return dto;
    }

    private Artist toEntity(ArtistDTO dto) {
        Artist artist = new Artist();
        artist.setId(dto.getId());
        artist.setName(dto.getName());
        artist.setBio(dto.getBio());
        artist.setCountry(dto.getCountry());
        return artist;
    }
}
