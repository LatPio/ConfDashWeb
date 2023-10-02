package com.flystonedev.localization.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity(name = "Map-Image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapImage {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "map_image_id_sequence",
            sequenceName = "map_image_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "map_image_id_sequence"
    )
    private Integer id;
    private String name;
    private String fileName;
    @OneToMany(mappedBy = "mapImage")
    private List<Localization> localization;
    @Lob
    private byte[] data;

}
