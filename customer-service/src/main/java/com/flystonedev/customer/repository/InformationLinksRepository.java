package com.flystonedev.customer.repository;


import com.flystonedev.customer.model.InformationLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationLinksRepository extends JpaRepository<InformationLinks, Integer> {

    Optional<InformationLinks> findInformationLinksByIdAndAuthId (Integer id, String authId);
    List<InformationLinks> findInformationLinksByAuthId(String authId);
}
