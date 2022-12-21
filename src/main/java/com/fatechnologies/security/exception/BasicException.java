package com.fatechnologies.security.exception;

import java.io.Serial;

public class BasicException extends BadRequestAlertException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BasicException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Veuillez contacter l'administrateur", "userManagement", "");
    }
}
