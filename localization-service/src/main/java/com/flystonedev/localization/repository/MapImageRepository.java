package com.flystonedev.localization.repository;

import com.flystonedev.localization.model.MapImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MapImageRepository extends JpaRepository<MapImage, Integer> {


}
