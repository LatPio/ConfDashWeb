package com.flystonedev.cutomer.repository;

import com.flystonedev.cutomer.model.ProfilePhoto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Integer> {

    @Transactional
    void deleteById(Integer integer);

    Optional<ProfilePhoto> findProfilePhotoByIdAndAndId (Integer id, String authId);
}
