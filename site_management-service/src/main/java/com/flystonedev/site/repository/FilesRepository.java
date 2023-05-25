package com.flystonedev.site.repository;

import com.flystonedev.site.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<Files, Integer> {
}
