package com.fatechnologies.security.exception;

public class UsernameAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super("L'identifiant existe déja");
    }
}
