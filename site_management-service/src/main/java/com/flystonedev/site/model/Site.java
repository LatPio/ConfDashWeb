package com.flystonedev.site.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Site")
public class Site {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "site_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "site_id_sequence"
    )
    private Integer id;
    private String name;
    private String body;
    private Integer orderNumber;
    private boolean visible;


    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;

}
