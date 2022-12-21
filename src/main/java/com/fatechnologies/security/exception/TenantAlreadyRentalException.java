package com.fatechnologies.security.exception;

public class TenantAlreadyRentalException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public TenantAlreadyRentalException() {
        super(ErrorConstants.TENANT_ALREADY_RENTAL, "Email is already in use!", "userManagement", "emailexists");
    }
}
