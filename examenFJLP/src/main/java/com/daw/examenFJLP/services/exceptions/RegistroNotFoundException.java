package com.daw.examenFJLP.services.exceptions;


public class RegistroNotFoundException extends RuntimeException {
    public RegistroNotFoundException(String message) {
        super(message);
    }
}