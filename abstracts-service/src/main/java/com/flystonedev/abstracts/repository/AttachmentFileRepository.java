package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.model.AttachmentFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Integer> {

    @Transactional
    void deleteById(Integer integer);

    List<AttachmentFile> findAttachmentFileByAuthId (String authId);

    List<AttachmentFile> findAttachmentFileByAbstractsEntity_Id(Integer id);
    Optional<AttachmentFile> findAttachmentFileByIdAndAuthId (Integer id, String authId);

    Optional<Void> deleteAttachmentFileByIdAndAuthId (Integer id, String authID);
}
