package org.example.projetodiogo.exceptions;

public class ValencaException extends RuntimeException {
    public ValencaException(String message) {
        super(message);
    }

    public ValencaException(String message, Throwable cause) {
        super(message, cause);
    }
}
