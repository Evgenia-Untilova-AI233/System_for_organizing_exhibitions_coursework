package ua.opnu.jenya.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.jenya.DTO.LocationDTO;
import ua.opnu.jenya.Model.Location;
import ua.opnu.jenya.Repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LocationDTO getLocationById(Long id) {
        return locationRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public LocationDTO createLocation(LocationDTO dto) {
        Location location = toEntity(dto);
        return toDto(locationRepository.save(location));
    }

    public LocationDTO updateLocation(Long id, LocationDTO dto) {
        return locationRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setAddress(dto.getAddress());
            existing.setCapacity(dto.getCapacity());
            return toDto(locationRepository.save(existing));
        }).orElse(null);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public List<LocationDTO> getTopLocationsByExhibitionCount() {
        return locationRepository.findTopLocationsByExhibitionCount()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LocationDTO toDto(Location entity) {
        LocationDTO dto = new LocationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setCapacity(entity.getCapacity());
        return dto;
    }

    private Location toEntity(LocationDTO dto) {
        Location entity = new Location();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setCapacity(dto.getCapacity());
        return entity;
    }
}
