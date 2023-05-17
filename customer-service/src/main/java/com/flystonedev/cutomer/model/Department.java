package com.flystonedev.cutomer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Street")
    private String street;
    @Column(name = "BuldingNumber")
    private String buildingNumber;
    @Column(name = "FlatNumber")
    private String flatNumber;
    @Column(name = "City")
    private String city;
    @Column(name = "PostalCode")
    private String postalCode;
    @Column(name = "Country")
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;
    @OneToMany(mappedBy = "department",
                cascade = CascadeType.ALL,
                orphanRemoval = false)
    private List<Customer> customers;
    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;
}
