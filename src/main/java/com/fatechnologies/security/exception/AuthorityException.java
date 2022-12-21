package com.fatechnologies.security.exception;

import java.io.Serial;

public class AuthorityException extends BadRequestAlertException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AuthorityException() {
        super(ErrorConstants.AUTHORITY_NOT_BLANK, "Email is already in use!", "userManagement", "email-exists");
    }
}
