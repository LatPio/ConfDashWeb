package com.flystonedev.event.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

@Entity
@Table(name = "Event_Type")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventType {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "eventType_id_sequence",
            sequenceName = "eventType_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "eventType_id_sequence"
    )
    private Integer id;
    private String name;
    private Duration time;
    private boolean locationConflict;
    private boolean timeConflict;

    @OneToMany(mappedBy = "eventType")
    private Collection<EventEntity> eventEntity;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;


}
