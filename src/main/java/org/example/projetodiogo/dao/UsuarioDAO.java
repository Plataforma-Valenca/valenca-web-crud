package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.DuplicateEmailException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.exceptions.InvalidCredentialsException;
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

            resultado = pstmt.executeUpdate() > 0;
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

    public boolean inserirProfessorComDisciplina(Usuario usuario, String disciplina) throws SQLException {
        String sqlUsuario = """
            INSERT INTO usuarios(nome, email, senha, cpf, tipo, username, cadastro_completo) 
            VALUES(?, ?, ?, ?, 'professor', ?, true)
            RETURNING id_usuario;
        """;

        String sqlProfessor = """
            INSERT INTO professores (id_usuario)
            VALUES (?)
            RETURNING id_professor;
        """;

        String sqlRelacao = """
            INSERT INTO disciplinas (nome, id_professor)
            VALUES (?, ?);
        """;

        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        Connection conn = null;

        ResultSet rs = null;
        ResultSet rs2 = null;

        Boolean resultado = false;

        try {
            if (ValidadorDeCampoUsado.ehCampoEmUso("usuarios", "email", usuario.getEmail()))
                throw new DuplicateEmailException(usuario.getEmail());
            conn = ConnectionFactory.conectar();

            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sqlUsuario);

            // Método para hashear a senha e guardar no banco após a criptografia
            String senhaHash = HasherSenha.hashSenha(usuario.getSenha());

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, senhaHash);
            pstmt.setString(4, usuario.getCpf());
            pstmt.setString(5, usuario.getUsername());

            rs = pstmt.executeQuery();

            rs.next();

            int idUsuario = rs.getInt("id_usuario");

            pstmt2 = conn.prepareStatement(sqlProfessor);

            pstmt2.setInt(1, idUsuario);

            rs2 = pstmt2.executeQuery();
            rs2.next();

            int idProfessor = rs2.getInt("id_professor");

            pstmt3 = conn.prepareStatement(sqlRelacao);

            pstmt3.setString(1, disciplina);
            pstmt3.setInt(2, idProfessor);

            pstmt3.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            System.out.println("[DAO] Erro ao inserir usuario: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null || pstmt2 != null || pstmt3 != null) pstmt.close();
                if (conn != null) ConnectionFactory.desconectar(conn);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }
        return resultado;
    }

    public int inserirNovoAluno(Usuario usuario) {
        String sql = "INSERT INTO usuarios(senha, cpf, tipo, cadastro_completo) VALUES(?, ?, 'aluno', false)";
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
    public Optional<Usuario> validarLogin(String matriculaOuNomeUsuario) {
        if (matriculaOuNomeUsuario.isEmpty()) {
            throw new InvalidCredentialsException();
        }



        String sql = """
                SELECT * FROM usuarios u
        LEFT JOIN alunos a ON u.id_usuario = a.id_usuario
        WHERE (u.username = ? OR a.matricula = ?)
        AND u.cadastro_completo = true
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            String login = matriculaOuNomeUsuario;

            pstmt.setString(1, login);

            if (login.matches("\\d+")) {
                pstmt.setLong(2, Long.parseLong(login));
            } else {
                pstmt.setNull(2, Types.BIGINT);
            }


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
            System.err.println("[DAO ERROR] Erro ao buscar usuário por matrícula ou nome de usuário: " + matriculaOuNomeUsuario);
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

    public Optional<Usuario> buscarPorCpfOuMatricula(String cpfOuMatricula) {
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


            pstmt.setString(1, cpfOuMatricula);
            pstmt.setLong(2, Long.parseLong(cpfOuMatricula));

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

    public Optional<Usuario> buscarPorIdProfessor(int idProfessor) {

        String sql = """
                SELECT * FROM usuarios u
                JOIN professores p ON p.id_usuario = u.id_usuario
                WHERE p.id_professor = ?;
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, idProfessor);

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
            } else {
                throw new EntityNotFoundException("Professor, ", idProfessor);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por id do professo: " + idProfessor);
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

    public Optional<Usuario> buscarPorEmail(String email) {

        String sql = """
                SELECT * FROM usuarios u
                WHERE email = ?;
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);


            pstmt.setString(1, email);

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
            } else {
                throw new EntityNotFoundException("Email, ", email);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por email: " + email);
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

    public void atualizarSenha(int idUsuario, String senhaNova) {

        String sql = """
        UPDATE usuarios
        SET senha = ?
        WHERE id_usuario = ?
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            String senhaHash = HasherSenha.hashSenha(senhaNova);

            pstmt.setString(1, senhaHash);
            pstmt.setInt(2, idUsuario);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum usuário encontrado para atualizar senha.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar senha", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
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
    public boolean atualizarNomePorAluno(int idAluno, String nome) {

        String sql = """
        UPDATE usuarios
        SET nome = ?
        WHERE id_usuario = (
            SELECT id_usuario
            FROM alunos
            WHERE id_aluno = ?
        )
    """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setInt(2, idAluno);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar nome: " + e.getMessage());
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
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, Long.parseLong(cpfOuMatricula));
            pstmt.setString(2, cpfOuMatricula);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        HasherSenha.hashSenha("senha"),
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
