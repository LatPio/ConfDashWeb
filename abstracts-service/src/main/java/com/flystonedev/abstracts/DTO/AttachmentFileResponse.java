package com.flystonedev.abstracts.DTO;

import com.flystonedev.abstracts.model.FileRole;

public record AttachmentFileResponse(
        Integer id,
        String name,
        String type,
        String url,
        FileRole role
) {
}
