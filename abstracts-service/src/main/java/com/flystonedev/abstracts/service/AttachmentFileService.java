package com.flystonedev.abstracts.service;

import com.flystonedev.abstracts.DTO.AttachmentFileDTO;
import com.flystonedev.abstracts.DTO.AttachmentFileRequest;
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

    @Transactional
    public void saveFile(MultipartFile file, AttachmentFileRequest attachmentFileRequest) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        AttachmentFile attachmentFile = AttachmentFile.builder()
                .name(filename)
                .type(file.getContentType())
                .fileRole(attachmentFileRequest.fileRole())
                .data(file.getBytes())
                .abstractsEntity(abstractRepository.findById(attachmentFileRequest.abstractsEntity().getId()).orElseThrow(EntityNotFoundException::new))
                .build();
        attachmentFileRepository.save(attachmentFile);
    }

    public List<AttachmentFileDTO> attachmentFileDTOS(){
        List<AttachmentFile> attachmentFiles = attachmentFileRepository.findAll();
        return attachmentFiles.stream().map(attachmentFileMapper::map).collect(Collectors.toList());
    }

    public AttachmentFileDTO get(Integer id){
        return attachmentFileRepository.findById(id).map(attachmentFileMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public AttachmentFileDTO update(AttachmentFileDTO attachmentFileDTO){
        AttachmentFileDTO exist = get(attachmentFileDTO.getId());
        if(exist == null){
            return null;
        }
        AttachmentFile updated = attachmentFileRepository.save(attachmentFileMapper.map(attachmentFileDTO));
        return attachmentFileMapper.map(updated);
    }

    public void delete(Integer id){ attachmentFileRepository.deleteById(id);}

}
