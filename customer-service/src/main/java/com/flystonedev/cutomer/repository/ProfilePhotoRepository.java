package com.flystonedev.cutomer.repository;

import com.flystonedev.cutomer.model.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Integer> {
}
