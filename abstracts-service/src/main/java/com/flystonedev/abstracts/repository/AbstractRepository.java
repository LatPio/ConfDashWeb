package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.model.AbstractsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbstractRepository extends JpaRepository<AbstractsEntity, Integer> {

    List<AbstractsEntity> findAbstractsEntitiesByAuthId (String authId);

    List<AbstractsEntity> findAbstractsEntitiesByAccepted (Boolean isAccepted);

    Long countAbstractsEntitiesByAccepted(Boolean isAccepted);

    long count();

    Optional<AbstractsEntity> findByIdAndAuthId(Integer Id, String authId);
    Optional<AbstractsEntity> findByIdAndAccepted(Integer Id, Boolean accepted);

    Optional<Void> deleteByIdAndAuthId (Integer id, String authId);
}
