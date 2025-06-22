package ua.opnu.jenya.DTO;

import lombok.Data;

@Data
public class ExhibitDTO {
    private Long id;
    private String title;
    private String description;
    private String type;
    private Integer yearCreated;
}
