package ua.opnu.jenya.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExhibitionDTO {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
}
