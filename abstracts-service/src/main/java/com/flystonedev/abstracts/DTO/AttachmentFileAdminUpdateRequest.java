package com.flystonedev.abstracts.DTO;

import com.flystonedev.abstracts.model.FileRole;

public record AttachmentFileAdminUpdateRequest(

        Integer id,
        FileRole fileRole,
        AbstractDTO abstractsEntity,
        String authId


) {
}
