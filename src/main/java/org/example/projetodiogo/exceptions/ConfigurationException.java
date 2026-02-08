package org.example.projetodiogo.exceptions;

public class ConfigurationException extends ValencaException {
    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}