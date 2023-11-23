package com.flystonedev.abstracts.DTO;

import com.flystonedev.abstracts.model.FileRole;

public record AttachmentFileAdminRequest(

        FileRole fileRole,
        AbstractDTO abstractsEntity,
        String authId


) {
}
