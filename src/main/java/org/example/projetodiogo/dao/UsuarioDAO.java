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

import java.sql.*;
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

    public int inserirNovoAluo(Usuario usuario) {
        String sql = "INSERT INTO usuarios(senha, cpf, tipo) VALUES(?, ?, ?)";
        int idGeradoUsuario = 0;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Método para hashear a senha e guardar no banco após a criptografia
            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            pstmt.setString(1, senhaHash);
            pstmt.setString(2, usuario.getCpf());
            pstmt.setString(3, "ALUNO");

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                idGeradoUsuario = rs.getInt(1);
                return idGeradoUsuario;
            }
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

        return idGeradoUsuario;
    }

    // READ
    public Optional<Usuario> validarLogin(String cpfMatriculaOuNomeUsuario) {
        if (cpfMatriculaOuNomeUsuario.isEmpty()) {
            throw new InvalidCredentialsException();
        }



        String sql = """
                SELECT * FROM usuarios u
        LEFT JOIN alunos a ON u.id_usuario = a.id_usuario
        WHERE (u.username = ? OR u.cpf = ? OR a.matricula = ?)
        AND u.cadastro_completo = true
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            int matricula = -1;
            try {
                matricula = Integer.parseInt(cpfMatriculaOuNomeUsuario);
            } catch (NumberFormatException e) {
            }
            
            pstmt.setString(1, cpfMatriculaOuNomeUsuario);
            pstmt.setString(2, cpfMatriculaOuNomeUsuario);
            pstmt.setString(3, cpfMatriculaOuNomeUsuario);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("tipo"),
                        rs.getBoolean("cadastro_completo")
                        );

                return Optional.of(usuario);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por email ou nome de usuário: " + cpfMatriculaOuNomeUsuario);
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

    public Optional<Usuario> finalizarCadastro(String cpfOuMatricula) {
        if (cpfOuMatricula.isEmpty()) {
            throw new InvalidCredentialsException();
        }



        String sql = """
                SELECT * FROM usuarios u
                LEFT JOIN alunos a ON u.id_usuario = a.id_usuario
                WHERE (u.cpf = ? OR a.matricula = ?)
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            int matricula = -1;
            try {
                matricula = Integer.parseInt(cpfOuMatricula);
            } catch (NumberFormatException e) {
            }

            pstmt.setString(1, cpfOuMatricula);
            pstmt.setInt(2, matricula);

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("tipo"),
                        rs.getBoolean("cadastro_completo")
                );

                return Optional.of(usuario);
            } else {
                throw new EntityNotFoundException("Usuario", cpfOuMatricula);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por cpf ou matrícula: " + cpfOuMatricula);
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
                SET nome = ?, email = ?, senha = ?
                WHERE  id_usuario = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, senhaHash);
            pstmt.setInt(4, usuario.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarPreCadastro(Usuario usuario) {

        String sql = """
                UPDATE usuarios
                SET nome = ?, email = ?,  senha = ?, cadastro_completo = ?
                WHERE  id_usuario = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, senhaHash);
            pstmt.setBoolean(4, usuario.isCadastroCompleto());
            pstmt.setInt(5, usuario.getId());

            return pstmt.executeUpdate() > 0;

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

    // VALIDAR PRIMEIRO ACESSO
    public Usuario validarPrimeiroAcesso(String cpfOuMatricula) {

        String sql = """
        SELECT *
        FROM alunos a
        JOIN usuarios u ON a.id_usuario = u.id_usuario
        WHERE (a.matricula = ? OR u.cpf = ?)
        AND u.cadastro_completo = false
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (ValidadorDeCampoUsado.ehCampoEmUso("alunos", "matricula", cpfOuMatricula)) throw new DuplicateEmailException(cpfOuMatricula);

            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            int matricula = 0;
            try {
                matricula = Integer.parseInt(cpfOuMatricula);
            } catch (NumberFormatException e) {
                matricula = -1;
            }

            pstmt.setInt(1, matricula);
            pstmt.setString(2, cpfOuMatricula);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario(
                        HasherSenha.hashSenha("123456"),
                        rs.getString("cpf"),
                        rs.getString("tipo"),
                        rs.getBoolean("cadastro_completo")
                );

                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao buscar primeiro acesso: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectionFactory.desconectar(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexões: " + e.getMessage());
            }
        }

        return null;
    }
}
