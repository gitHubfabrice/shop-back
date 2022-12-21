package com.fatechnologies.security.exception;

public class QuantityException extends BadRequestAlertException {

    public QuantityException() {
        super(ErrorConstants.AUTHORITY_NOT_BLANK, "La quantité n'est pas disponible!", "Stock", "quantityMoreThan");
    }
}
