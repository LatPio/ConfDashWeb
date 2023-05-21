package com.flystonedev.event.repository;

import com.flystonedev.event.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, Integer> {
}
