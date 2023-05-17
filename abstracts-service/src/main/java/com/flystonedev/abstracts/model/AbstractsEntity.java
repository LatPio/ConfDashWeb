package com.flystonedev.abstracts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "Abstracts")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractsEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "abstract_id_sequence",
            sequenceName = "abstract_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "abstract_id_sequence"
    )
    private Integer id;
    private String abstractTitle;
    private String body;
    private byte[] graphicalAbstract ;
    private String authors;
    private String affiliation;
    private byte[] figure;
    private Integer presenterId;
    private Integer ownerId;
    private boolean accepted;
    private byte[] abstractFile;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;

}
