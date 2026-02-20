package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Aluno;
import java.sql.Connection;
import org.example.projetodiogo.util.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;






public class AlunoDAO {

    // VINCULAR CADA ALUNO COM TODAS AS DISCIPLINAS
    public void vincularAlunoADisciplinas(int idAluno) {
        String sql = """
        INSERT INTO notas (id_aluno, id_disciplina)
        SELECT ?, d.id_disciplina
        FROM disciplinas d
        WHERE NOT EXISTS (
            SELECT 1
            FROM notas n
            WHERE n.id_aluno = ?
            AND n.id_disciplina = d.id_disciplina
        )
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idAluno);
            pstmt.setInt(2, idAluno);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao vincular aluno com as disciplinas: " + e.getMessage());
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }
    }



    // INSERT
    public boolean insert(Aluno aluno) {
        String query = """
                INSERT INTO alunos
                (id_usuario, matricula)
                VALUES (?, ?)
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, aluno.getIdUsuario());
            pstmt.setInt(2, aluno.getMatricula());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
            return false;
        }
    }

    // READ
    public Optional<Aluno> select(int id_aluno) {

        String query = """
                SELECT * FROM alunos
                WHERE id_aluno = ?;
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id_aluno);

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id_aluno"));
                aluno.setIdUsuario(rs.getInt("id_usuario"));
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setDtMatricula(rs.getTimestamp("dt_matricula"));

                return Optional.of(aluno);
            } else {
                throw new EntityNotFoundException("Aluno", id_aluno);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar aluno pelo id: " + id_aluno);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar aluno", e);
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

    public Optional<Aluno> buscarPorIdUsuario(int idUsuario) {

        String query = """
                SELECT * FROM alunos
                WHERE id_usuario = ?;
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);

            if (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getInt("id_usuario"),
                        rs.getInt("matricula"),
                        rs.getTimestamp("dt_matricula")
                );

                return Optional.of(aluno);
            } else {
                throw new EntityNotFoundException("Aluno", idUsuario);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar aluno pelo id: " + idUsuario);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar aluno", e);
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
    public boolean update(Aluno aluno) {

        String query = """
                UPDATE alunos
                SET id_aluno = ?, id_usuario = ?,  matricula = ?,  dt_matricula = ?
                WHERE  id_aluno = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, aluno.getId());
            ps.setInt(2, aluno.getIdUsuario());
            ps.setInt(3, aluno.getMatricula());
            ps.setTimestamp(4, aluno.getDtMatricula());
            ps.setInt(5, aluno.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean delete(int id_aluno) {

        String query = """
                DELETE FROM alunos 
                WHERE id_aluno = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_aluno);

            return ps .executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
            return false;
        }
    }
}