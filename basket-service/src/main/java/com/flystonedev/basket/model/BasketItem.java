package com.flystonedev.basket.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "Basket")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {

    @Id
    @Column
    @SequenceGenerator(
            name = "basket_id_sequence",
            sequenceName = "basket_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "basket_id_sequence"
    )
    private Integer id;
    private String name;
    private Integer eventId;
    private Boolean deletable;
    @Column(nullable = false, name = "AuthId")
    private String authId;

    @CreationTimestamp
    @Column(name = "Creation_Date", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "Update_Date")
    private Instant updatedAt;


}
