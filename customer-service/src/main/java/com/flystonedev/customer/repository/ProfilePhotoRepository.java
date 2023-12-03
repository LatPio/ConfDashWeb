package com.flystonedev.customer.repository;

import com.flystonedev.customer.model.ProfilePhoto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Integer> {

    @Transactional
    void deleteById(Integer integer);
    Optional<ProfilePhoto> findProfilePhotoByIdAndAuthId (Integer id, String authId);
}
