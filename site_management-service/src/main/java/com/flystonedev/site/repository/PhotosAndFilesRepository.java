package com.flystonedev.site.repository;

import com.flystonedev.site.model.PhotosAndFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosAndFilesRepository extends JpaRepository<PhotosAndFiles, Integer> {
}
