package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    public boolean inserirAluno(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, email, senha, tipo) VALUES(?, ?, ?, ?)";

        PreparedStatement pstmt = null;
        Connection conn = null;

        return false;
    }
}
