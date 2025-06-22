package ua.opnu.jenya.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    private int capacity;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Exhibition> exhibitions = new ArrayList<>();
}
