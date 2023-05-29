package com.flystonedev.event.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Table(name = "Event")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "event_id_sequence",
            sequenceName = "event_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_id_sequence"
    )
    private Integer id;
    private String name;
    private String abstractId;
    private String localizationId;
    private String localizationName;
    @ManyToOne
    @JoinColumn(name = "event_Type_ID")
    private EventType eventType;
    private LocalDateTime dateTimeOfEvent;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;




}
