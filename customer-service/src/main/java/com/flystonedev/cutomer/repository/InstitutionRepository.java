package com.flystonedev.cutomer.repository;


import com.flystonedev.cutomer.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    Institution findInstitutionByName (String name);
}
