package com.flystonedev.abstracts.service;

import com.flystonedev.abstracts.DTO.AttachmentFileAdminRequest;
import com.flystonedev.abstracts.DTO.AttachmentFileDTO;
import com.flystonedev.abstracts.DTO.AttachmentFileRequest;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.exeption.AbstractEditionBlockedException;
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
    public void saveUsersFile(MultipartFile file, AttachmentFileRequest attachmentFileRequest) throws IOException{
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

    public AttachmentFileDTO updateUsersFile(AttachmentFileDTO attachmentFileDTO){
        AttachmentFileDTO exist = getUserFile(attachmentFileDTO.getId());
        if(exist == null){
            throw new EntityNotFoundException();
        } else
        if (exist.getAccepted()) {
            throw new AbstractEditionBlockedException();
        } else {
            AttachmentFile updated = attachmentFileRepository.save(attachmentFileMapper.map(attachmentFileDTO));
            return attachmentFileMapper.map(updated);
        }
    }

    public void deleteUsersFile(Integer id){
        AttachmentFileDTO exist = getUserFile(id);
        if(exist == null){
            throw new EntityNotFoundException();
        } else
        if (exist.getAccepted()) {
            throw new AbstractEditionBlockedException();
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
    public void saveAdminFile(MultipartFile file, AttachmentFileAdminRequest attachmentFileAdminRequest) throws IOException{
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
    }

    public List<AttachmentFileDTO> attachmentAdminFileDTOS(){
        List<AttachmentFile> attachmentFiles = attachmentFileRepository.findAll();
        return attachmentFiles.stream().map(attachmentFileMapper::map).collect(Collectors.toList());
    }

    public AttachmentFileDTO getAdmin(Integer id){
        return attachmentFileRepository.findById(id).map(attachmentFileMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public AttachmentFileDTO updateAdmin(AttachmentFileDTO attachmentFileDTO){
        AttachmentFileDTO exist = getAdmin(attachmentFileDTO.getId());
        if(exist == null){
            return null;
        }
        AttachmentFile updated = attachmentFileRepository.save(attachmentFileMapper.map(attachmentFileDTO));
        return attachmentFileMapper.map(updated);
    }

    public void deleteAdmin(Integer id){ attachmentFileRepository.deleteById(id);}

}
