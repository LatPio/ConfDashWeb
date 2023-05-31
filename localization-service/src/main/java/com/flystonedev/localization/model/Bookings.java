package com.flystonedev.localization.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "bookings_id_sequence",
            sequenceName = "bookings_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookings_id_sequence"
    )
    private Integer id;
    private Integer eventIDData;
    private boolean locationConflict;
    private boolean timeConflict;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    @ManyToOne()
    @JoinColumn()
    private Localization localization;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;
}
