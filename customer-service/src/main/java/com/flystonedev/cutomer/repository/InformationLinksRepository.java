package com.flystonedev.cutomer.repository;


import com.flystonedev.cutomer.model.InformationLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationLinksRepository extends JpaRepository<InformationLinks, Integer> {
}
