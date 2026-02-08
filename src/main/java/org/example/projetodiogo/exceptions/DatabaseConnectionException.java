package org.example.projetodiogo.exceptions;

public class DatabaseConnectionException extends ValencaException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super("Erro ao conectar com o banco de dados: " + message, cause);
    }
}