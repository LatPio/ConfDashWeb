package com.flystonedev.abstracts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Attachemt_File")
public class AttachmentFile {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "attachmentFile_id_sequence",
            sequenceName = "attachmentFile_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attachmentFile_id_sequence"
    )
    private Integer id;
    private String name;
    private String type;
    private FileRole fileRole;
    @Lob
    private byte[] data;

    @ManyToOne()
    @JoinColumn()
    private AbstractsEntity abstractsEntity;
}
