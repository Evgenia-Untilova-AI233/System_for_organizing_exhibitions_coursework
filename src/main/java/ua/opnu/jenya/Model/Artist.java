package ua.opnu.jenya.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @Column(length = 2000)
    private String bio;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Exhibit> exhibits = new ArrayList<>();
}
