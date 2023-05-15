package com.flystonedev.cutomer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InformationLinks {

    @Id
    private Integer id;
    private String name;
    private String urlLink;
    @ManyToOne()
    private Customer customer;
}
