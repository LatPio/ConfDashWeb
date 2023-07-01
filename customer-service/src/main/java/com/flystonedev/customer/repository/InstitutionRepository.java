package com.flystonedev.customer.repository;


import com.flystonedev.customer.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    Institution findInstitutionByName (String name);
}
