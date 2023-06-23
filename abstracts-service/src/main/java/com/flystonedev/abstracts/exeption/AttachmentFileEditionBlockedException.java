package com.flystonedev.abstracts.exeption;


import com.flystonedev.abstracts.exeption.config.GlobalErrorCode;

public class AttachmentFileEditionBlockedException extends ErrorWrapper{

    public AttachmentFileEditionBlockedException() {
        super("File edition blocked", GlobalErrorCode.ERROR_ABSTRACT_FILE_BLOCKED_FOR_EDITION);
    }
    public AttachmentFileEditionBlockedException(String message, Long code) {
        super(message, code);
    }
}
