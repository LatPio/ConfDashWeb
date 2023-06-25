package com.flystonedev.abstracts.DTO;

import com.flystonedev.abstracts.model.FileRole;

public record AttachmentFileUserUpdateRequest(

        Integer id,
        FileRole fileRole,
        AbstractDTO abstractsEntity


) {
}
