package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.model.AbstractsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractRepository extends JpaRepository<AbstractsEntity, Integer> {
}
