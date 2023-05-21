package com.flystonedev.localization.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "localization_id_sequence",
            sequenceName = "localization_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "localization_id_sequence"
    )
    private Integer id;
    private String building;
    private String room;
    private String flor;
    private String linkToExternalMap;
    private Integer coordinateX;
    private Integer coordinateY;
    private byte[] mapImage;
}
