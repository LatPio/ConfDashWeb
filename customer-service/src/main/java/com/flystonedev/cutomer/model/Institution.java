package com.flystonedev.cutomer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Institution {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "institution",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Department> department;


    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;
}
