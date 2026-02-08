package org.example.projetodiogo.exceptions;

public class DataAccessException extends ValencaException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
