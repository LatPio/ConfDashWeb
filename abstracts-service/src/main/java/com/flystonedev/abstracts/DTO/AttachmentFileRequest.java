package com.flystonedev.abstracts.DTO;

import com.flystonedev.abstracts.model.FileRole;

public record AttachmentFileRequest(

        FileRole fileRole,
        AbstractDTO abstractsEntity
) {
}
