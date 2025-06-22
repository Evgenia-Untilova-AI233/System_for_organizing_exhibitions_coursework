package ua.opnu.jenya.DTO;

import lombok.Data;

@Data
public class ExhibitionEntryDTO {
    private Long id;
    private Long artistId;
    private Long exhibitId;
    private Long exhibitionId;
    private String placement;
}
