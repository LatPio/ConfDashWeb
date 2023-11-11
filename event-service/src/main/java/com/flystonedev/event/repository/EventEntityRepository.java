package com.flystonedev.event.repository;

import com.flystonedev.event.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findByIdIn(List<Integer> listOfEvents);

    List<EventEntity> findEventEntitiesByLocalizationId( String id);
}
