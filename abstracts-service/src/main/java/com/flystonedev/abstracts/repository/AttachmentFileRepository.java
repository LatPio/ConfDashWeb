package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.model.AttachmentFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Integer> {

    @Transactional
    void deleteById(Integer integer);
}
