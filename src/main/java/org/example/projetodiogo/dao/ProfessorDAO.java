package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Professor;
import java.sql.Connection;

import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;



public class ProfessorDAO {

    // INSERT
    public boolean insert(Professor professor) {

        String query = """
                INSERT INTO professores
                (id_professor, id_usuario)
                VALUES (?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, professor.getId());
            ps.setInt(2, professor.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
            return false;
        }
    }

    // READ
    public Optional<Professor> select(int id_professor) {

        String query = """
                SELECT * FROM professores
                WHERE id_professor = ?;
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id_professor);

            if (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id_professor"));
                professor.setIdUsuario(rs.getInt("id_usuario"));

                return Optional.of(professor);
            } else {
                throw new EntityNotFoundException("Professor", id_professor);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar professor pelo id: " + id_professor);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar professor", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public Optional<Professor> buscarProfessorPorIdUsuario(int idUsuario) {

        String query = """
                SELECT * FROM professores
                WHERE id_usuario = ?;
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);

            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("id_professor"),
                        rs.getInt("id_usuario")
                );

                return Optional.of(professor);
            } else {
                throw new EntityNotFoundException("Professor", idUsuario);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar professor pelo id_professor: " + idUsuario);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar professor", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    // UPDATE
    public boolean update(Professor professor) {

        String query = """
                UPDATE professores
                SET id_professor = ?, id_usuario = ?
                WHERE  id_professor = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, professor.getId());
            ps.setInt(2, professor.getIdUsuario());
            ps.setInt(3, professor.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean delete(int id_professor) {

        String query = """
                DELETE FROM professor 
                WHERE id_professor = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_professor);

            return ps .executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
            return false;
        }
    }
}
