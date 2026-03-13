package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Professor;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.ConnectionFactory;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorDAO {

    // INSERT
    public boolean insert(Professor professor) {
        String query = """
                INSERT INTO professores (id_professor, id_usuario)
                VALUES (?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, professor.getId());
            ps.setInt(2, professor.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir professor", e);
        }
    }

    // READ POR ID
    public Optional<Professor> select(int id_professor) {
        String query = """
                SELECT * FROM professores
                WHERE id_professor = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_professor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Professor professor = new Professor(
                            rs.getInt("id_professor"),
                            rs.getInt("id_usuario")
                    );
                    return Optional.of(professor);
                } else {
                    return Optional.empty(); // 🔥 melhor prática
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar professor", e);
        }
    }

    // BUSCAR POR ID DO USUARIO
    public Optional<Professor> buscarProfessorPorIdUsuario(int idUsuario) {
        String query = """
                SELECT * FROM professores
                WHERE id_usuario = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Professor professor = new Professor(
                            rs.getInt("id_professor"),
                            rs.getInt("id_usuario")
                    );
                    return Optional.of(professor);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar professor por id_usuario", e);
        }
    }

    // LISTAR TODOS (🔥 FALTAVA ISSO PRA FAZER O JSP)
    public List<Professor> listarProfessores() {
        String query = "SELECT * FROM professores";
        List<Professor> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("id_professor"),
                        rs.getInt("id_usuario")
                );
                lista.add(professor);
            }

            return lista;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao listar professores", e);
        }
    }

    // UPDATE
    public boolean update(Professor professor) {
        String query = """
                UPDATE professores
                SET id_usuario = ?
                WHERE id_professor = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, professor.getIdUsuario());
            ps.setInt(2, professor.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao atualizar professor", e);
        }
    }

    // DELETE
    public boolean delete(int id_professor) {
        String query = """
                DELETE FROM professores
                WHERE id_professor = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_professor);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar professor", e);
        }
    }
    public List<Usuario> buscarTodosComUsuario() {

        String sql = """
        SELECT u.*
        FROM professores p
        JOIN usuarios u ON p.id_usuario = u.id_usuario
    """;

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar professores", e);
        }

        return lista;
    }
    public List<Usuario> buscarPorNomeOuEmail(String busca) {

        String sql = """
        SELECT u.*
        FROM professores p
        JOIN usuarios u ON p.id_usuario = u.id_usuario
        WHERE LOWER(u.nome) LIKE ? OR LOWER(u.email) LIKE ?
    """;

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + busca.toLowerCase() + "%";

            ps.setString(1, filtro);
            ps.setString(2, filtro);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar professores", e);
        }

        return lista;
    }
}