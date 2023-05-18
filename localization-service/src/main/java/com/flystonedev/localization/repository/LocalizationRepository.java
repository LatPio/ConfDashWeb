package com.flystonedev.localization.repository;

import com.flystonedev.localization.model.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Integer> {
}
