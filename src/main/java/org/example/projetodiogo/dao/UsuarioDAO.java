package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.DuplicateEmailException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.exceptions.InvalidCredentialsException;
import org.example.projetodiogo.model.AlunoConsultaDTO;
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

    // CREATE
    public boolean inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, email, senha, cpf, tipo) VALUES(?, ?, ?, ?, ?)";

        boolean resultado = false;

        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            if (ValidadorDeCampoUsado.ehCampoEmUso("usuarios", "email", usuario.getEmail())) throw new DuplicateEmailException(usuario.getEmail());
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            // Método para hashear a senha e guardar no banco após a criptografia
            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, senhaHash);
            pstmt.setString(4, usuario.getCpf());
            pstmt.setString(5, usuario.getTipoUsuario().toUpperCase());

            return (pstmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao inserir usuario: " + e.getMessage());
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

    // READ
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

    public Optional<AlunoConsultaDTO> buscarPorCpf(String cpf) {
        if (cpf.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        String sql = """
                SELECT
                    u.nome,
                    a.matricula,
                    u.cpf,
                    t.nome AS turma
                
                FROM usuarios u
                         JOIN alunos a ON a.id_usuario = u.id_usuario
                         JOIN aluno_turma at ON at.id_aluno = a.id_aluno
                         JOIN turmas t ON t.id_turma = at.id_turma
                
                WHERE u.cpf = ?;
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);

            if (rs.next()) {
                AlunoConsultaDTO consultaDTO = new AlunoConsultaDTO(
                        rs.getString("nome"),
                        rs.getString("matricula"),
                        rs.getString("cpf"),
                        rs.getString("turma")
                );

                return Optional.of(consultaDTO);
            } else {
                throw new EntityNotFoundException("Usuario", cpf);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por cpf: " + cpf);
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

    // UPDATE
    public boolean atualizar(Usuario usuario) {

        String sql = """
                UPDATE usuarios
                SET nome = ?, email = ?,  senha = ?
                WHERE  id_usuario = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, senhaHash);
            stmt.setInt(4, usuario.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deletar(int id_usuario) {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_usuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }
}
