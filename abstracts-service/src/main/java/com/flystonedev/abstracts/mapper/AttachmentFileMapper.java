package com.flystonedev.abstracts.mapper;

import com.flystonedev.abstracts.DTO.AttachmentFileDTO;
import com.flystonedev.abstracts.model.AttachmentFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentFileMapper {

    AttachmentFileDTO map(AttachmentFile attachmentFile);

    AttachmentFile map(AttachmentFileDTO attachmentFileDTO);
}
