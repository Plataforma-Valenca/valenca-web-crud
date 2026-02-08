package org.example.projetodiogo.dao;

import jakarta.security.enterprise.identitystore.PasswordHash;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.DuplicateEmailException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.exceptions.InvalidCredentialsException;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.ConnectionFactory;
import org.example.projetodiogo.util.HasherSenha;
import org.example.projetodiogo.util.ValidadorDeCampoUsado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioDAO {

    public boolean inserirAluno(Usuario usuario) {
        String sql = "INSERT INTO usuario(id, nome, email, senha, tipo, cpf) VALUES(?, ?, ?, ?, ?, ?)";

        boolean resultado = false;

        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            if (ValidadorDeCampoUsado.ehCampoEmUso("usuario", "email", usuario.getEmail())) throw new DuplicateEmailException(usuario.getEmail());
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            // Método para hashear a senha e guardar no banco após a criptografia
            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            pstmt.setInt(1, usuario.getId());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setString(5, usuario.getTipoUsuario());
            pstmt.setString(6, usuario.getCpf());

            return (pstmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao inserir aluno: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectionFactory.desconectar(conn);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }

        return resultado;
    }

    public Optional<Usuario> buscarPorEmailOuNomeUsuario(String emailOuNomeUsuario, String senha) {
        if (emailOuNomeUsuario.isEmpty() || senha.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        
        String sql = """
                SELECT FROM usuarios
                WHERE (email = ? OR nome = ?) AND senha = ?
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emailOuNomeUsuario);
            pstmt.setString(2, emailOuNomeUsuario);
            pstmt.setString(3, senha);
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setemail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipo"));
                usuario.setCpf(rs.getString("cpf"));

                return Optional.of(usuario);
            } else {
                throw new EntityNotFoundException("Usuario", emailOuNomeUsuario);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por email ou nome de usuário: " + emailOuNomeUsuario);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuário", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }
}
