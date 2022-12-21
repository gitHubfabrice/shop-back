package com.fatechnologies.security.exception;

public class Exception extends BadRequestAlertException {

    public Exception(String label) {
        super( label, "Prix", "message");
    }
}
