package com.flystonedev.localization.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Table
@Entity(name = "Localization")
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
    @OneToMany(mappedBy = "localization")
    private List<Bookings> bookings;
    private String linkToExternalMap;
    private Integer coordinateX;
    private Integer coordinateY;
    @ManyToOne
    @JoinColumn
    private MapImage mapImage;
//    private byte[] mapImage;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;
}
