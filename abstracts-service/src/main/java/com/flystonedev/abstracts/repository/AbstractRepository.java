package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.model.AbstractsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbstractRepository extends JpaRepository<AbstractsEntity, Integer> {

}
