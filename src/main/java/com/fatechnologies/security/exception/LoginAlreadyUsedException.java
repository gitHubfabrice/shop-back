package com.fatechnologies.security.exception;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "l'identifiant existe déja!", "accountManagement", "l'identifiant existe déja!");
    }
}
