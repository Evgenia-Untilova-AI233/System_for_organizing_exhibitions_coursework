package ua.opnu.jenya.DTO;

import lombok.Data;

@Data
public class LocationDTO {
    private Long id;
    private String name;
    private String address;
    private int capacity;
}