package com.flystonedev.abstracts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Table(name = "Abstracts")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractsEntity {

    @Id
    @Column
    @SequenceGenerator(
            name = "abstract_id_sequence",
            sequenceName = "abstract_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "abstract_id_sequence"
    )
    private Integer id;
    @Column(name = "Abstract_Titile")
    private String abstractTitle;
    @Lob
    private String body;
    private String authors;
    private String affiliation;
    private Integer ownerId;
    @Column(nullable = false, name = "AuthId")
    private String authId;
    private boolean accepted;
    private String comments;


    @OneToMany(mappedBy = "abstractsEntity", orphanRemoval = true)
    private List<AttachmentFile> files;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;

}
