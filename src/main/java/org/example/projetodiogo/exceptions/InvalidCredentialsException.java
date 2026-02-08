package org.example.projetodiogo.exceptions;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException() {
        super("Email ou senha incorretos");
    }
}
