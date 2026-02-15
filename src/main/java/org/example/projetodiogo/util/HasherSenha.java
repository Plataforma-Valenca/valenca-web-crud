package org.example.projetodiogo.util;

import org.mindrot.jbcrypt.BCrypt;

public class HasherSenha {

    private final static int workload = 10;

    // Método responsável por fazer o hash da senha original
    public static String hashSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(workload));
    }

    // Método que verifica se a senha hasheada é compatível com a senha original
    public static boolean verificaSenha(String senha, String senhaHash) {
        return BCrypt.checkpw(senha, senhaHash);
    }
}
