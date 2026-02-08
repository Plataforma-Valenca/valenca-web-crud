package org.example.projetodiogo.exceptions;

import jakarta.validation.ValidationException;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String email) {
        super();
    }
}