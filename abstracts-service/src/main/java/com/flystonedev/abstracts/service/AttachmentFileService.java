package com.flystonedev.abstracts.service;

import com.flystonedev.abstracts.DTO.*;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.exeption.AttachmentFileEditionBlockedException;
import com.flystonedev.abstracts.exeption.EntityNotFoundException;
import com.flystonedev.abstracts.mapper.AttachmentFileMapper;
import com.flystonedev.abstracts.model.AttachmentFile;
import com.flystonedev.abstracts.repository.AbstractRepository;
import com.flystonedev.abstracts.repository.AttachmentFileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttachmentFileService {

    private final AttachmentFileRepository attachmentFileRepository;
    private final AttachmentFileMapper attachmentFileMapper = Mappers.getMapper(AttachmentFileMapper.class);

    private final AbstractRepository abstractRepository;

    private final JwtConverter jwtConverter;

    /*
     *
     * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
     *
     * */

    @Transactional
    public AttachmentFileDTO saveUsersFile(MultipartFile file, AttachmentFileRequest attachmentFileRequest) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        AttachmentFile attachmentFile = AttachmentFile.builder()
                .name(filename)
                .type(file.getContentType())
                .fileRole(attachmentFileRequest.fileRole())
                .data(file.getBytes())
                .authId(jwtConverter.getKeycloakUserID())
                .accepted(false)
                .abstractsEntity(abstractRepository.findById(attachmentFileRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new))
                .build();
        attachmentFileRepository.save(attachmentFile);
        return attachmentFileMapper.map(attachmentFile);

    }

    public List<AttachmentFileDTO> attachmentUsersFileDTOS(){
        List<AttachmentFile> attachmentFiles = attachmentFileRepository.findAttachmentFileByAuthId(jwtConverter.getKeycloakUserID());
        return attachmentFiles.stream().map(attachmentFileMapper::map).collect(Collectors.toList());
    }

    public AttachmentFileDTO getUserFile(Integer id){
        return attachmentFileRepository
                .findAttachmentFileByIdAndAuthId(id, jwtConverter.getKeycloakUserID())
                .map(attachmentFileMapper::map)
                .orElseThrow(EntityNotFoundException::new);
    }

    public AttachmentFileDTO updateUsersFile(MultipartFile file, AttachmentFileUserUpdateRequest attachmentFileUserUpdateRequest) throws IOException{
        AttachmentFile exist = attachmentFileMapper.map(getUserFile(attachmentFileUserUpdateRequest.id()));
        if(exist == null){
            throw new EntityNotFoundException();
        } else
        if (exist.getAccepted()) {
            throw new AttachmentFileEditionBlockedException();
        } else if (file.getBytes().length !=0){
            exist.setData(file.getBytes());
            exist.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            exist.setType(file.getContentType());
            exist.setFileRole(attachmentFileUserUpdateRequest.fileRole());
            exist.setAbstractsEntity(
                    abstractRepository.findById(attachmentFileUserUpdateRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new));


            AttachmentFile updated = attachmentFileRepository.save(exist);
            return attachmentFileMapper.map(updated);
        } else {
            exist.setFileRole(attachmentFileUserUpdateRequest.fileRole());
            exist.setAbstractsEntity(
                    abstractRepository.findById(attachmentFileUserUpdateRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new));



            AttachmentFile updated = attachmentFileRepository.save(exist);
            return attachmentFileMapper.map(updated);

        }
    }

    @Transactional
    public void deleteUsersFile(Integer id){
        AttachmentFileDTO exist = getUserFile(id);
        if(exist == null){
            throw new EntityNotFoundException();
        } else
        if (exist.getAccepted()) {
            throw new AttachmentFileEditionBlockedException();
        } else {
            attachmentFileRepository
                    .deleteAttachmentFileByIdAndAuthId(id, jwtConverter.getKeycloakUserID());
        }
    }

    /*
     *
     * !!!!!!!!! ADMIN SERVICE METHODS !!!!!!!!
     *
     * */

    @Transactional
    public AttachmentFileDTO saveAdminFile(MultipartFile file, AttachmentFileAdminRequest attachmentFileAdminRequest) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        AttachmentFile attachmentFile = AttachmentFile.builder()
                .name(filename)
                .type(file.getContentType())
                .fileRole(attachmentFileAdminRequest.fileRole())
                .data(file.getBytes())
                .authId(attachmentFileAdminRequest.authId())
                .accepted(attachmentFileAdminRequest.accepted())
                .abstractsEntity(abstractRepository.findById(attachmentFileAdminRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new))
                .build();
        attachmentFileRepository.save(attachmentFile);
        return attachmentFileMapper.map(attachmentFile);
    }

    public List<AttachmentFileDTO> attachmentAdminFileDTOS(){
        List<AttachmentFile> attachmentFiles = attachmentFileRepository.findAll();
        return attachmentFiles.stream().map(attachmentFileMapper::map).collect(Collectors.toList());
    }

    public AttachmentFileDTO getAdminFile(Integer id){
        return attachmentFileRepository.findById(id).map(attachmentFileMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public AttachmentFileDTO updateAdmin(MultipartFile file, AttachmentFileAdminUpdateRequest attachmentFileAdminUpdateRequest) throws IOException {
        AttachmentFile exist = attachmentFileMapper.map(getAdminFile(attachmentFileAdminUpdateRequest.id()));


        if(exist == null){
            throw new EntityNotFoundException();
        } else if (file.getBytes().length !=0){
            exist.setData(file.getBytes());
            exist.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            exist.setType(file.getContentType());

            exist.setAccepted(attachmentFileAdminUpdateRequest.accepted());
            exist.setAuthId(attachmentFileAdminUpdateRequest.authId());
            exist.setFileRole(attachmentFileAdminUpdateRequest.fileRole());
            exist.setAbstractsEntity(
                    abstractRepository.findById(attachmentFileAdminUpdateRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new));


            AttachmentFile updated = attachmentFileRepository.save(exist);
            return attachmentFileMapper.map(updated);
        } else {

            exist.setAccepted(attachmentFileAdminUpdateRequest.accepted());
            exist.setAuthId(attachmentFileAdminUpdateRequest.authId());
            exist.setFileRole(attachmentFileAdminUpdateRequest.fileRole());
            exist.setAbstractsEntity(
                    abstractRepository.findById(attachmentFileAdminUpdateRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new));


            AttachmentFile updated = attachmentFileRepository.save(exist);
            return attachmentFileMapper.map(updated);
        }

    }

    public void updateAdnBlockAttachmentFilesByAdmin(Integer id, Boolean accepted){
        List<AttachmentFile> attachmentFileByAbstractsEntityId = attachmentFileRepository.findAttachmentFileByAbstractsEntity_Id(id);
        //todo block these files for modification
        attachmentFileByAbstractsEntityId.stream().forEach(attachmentFile -> {
            attachmentFile.setAccepted(accepted);
            attachmentFileRepository.save(attachmentFile);
        });
    }

    public void deleteAdmin(Integer id){ attachmentFileRepository.deleteById(id);}

}
