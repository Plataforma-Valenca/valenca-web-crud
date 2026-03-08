package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Aluno;

import java.sql.*;

import org.example.projetodiogo.util.ConnectionFactory;

import java.util.Optional;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;






public class AlunoDAO {

    // VINCULAR CADA ALUNO COM TODAS AS DISCIPLINAS E ALGUMA TURMA
    public void vincularAlunoADisciplinasTurma(int idAluno, int idTurma) throws SQLException {
        String sqlAlunoTurma = """
        INSERT INTO aluno_turma (id_aluno, id_turma, dt_entrada)
        SELECT ?, ?, CURRENT_DATE
        WHERE NOT EXISTS (
            SELECT 1
            FROM aluno_turma
            WHERE id_aluno = ?
            AND id_turma = ?
        )
    """;

        String sqlNotas = """
        INSERT INTO notas (id_aluno, id_disciplina)
        SELECT a.id_aluno, d.id_disciplina
        FROM alunos a
                 JOIN aluno_turma at ON at.id_aluno = a.id_aluno
                 JOIN turmas t ON t.id_turma = at.id_turma
                 JOIN disciplinas d ON at.id_turma = t.id_turma
        WHERE a.id_aluno = ?
          AND NOT EXISTS (
            SELECT 1
            FROM notas n
            WHERE n.id_aluno = a.id_aluno
              AND n.id_disciplina = d.id_disciplina
        );
    """;

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = ConnectionFactory.conectar();
            if (conn != null) conn.setAutoCommit(false);

            pstmt1 = conn.prepareStatement(sqlAlunoTurma);
            pstmt1.setInt(1, idAluno);
            pstmt1.setInt(2, idTurma);
            pstmt1.setInt(3, idAluno);
            pstmt1.setInt(4, idTurma);
            pstmt1.executeUpdate();

            pstmt2 = conn.prepareStatement(sqlNotas);
            pstmt2.setInt(1, idAluno);
            pstmt2.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao vincular aluno com as disciplinas: " + e.getMessage());
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt1 != null && pstmt2 != null) pstmt1.close(); pstmt2.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }
    }

    // INSERT
    public int inserir(int idUsuario) {
        String query = """
                INSERT INTO alunos
                (id_usuario)
                VALUES (?)
                """;
        int idGeradoUsuario = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, idUsuario);

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                idGeradoUsuario = rs.getInt(1);
                return idGeradoUsuario;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
            return idGeradoUsuario;
        }
    }

    // READ
    public Optional<Aluno> buscarAlunos() throws SQLException {

        String query = """
                SELECT * FROM alunos
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id_aluno"));
                aluno.setIdUsuario(rs.getInt("id_usuario"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setDtMatricula(rs.getTimestamp("dt_matricula"));

                return Optional.of(aluno);
            } else {
                throw new EntityNotFoundException("Aluno", rs.getInt("id_aluno"));
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar aluno pelo id: " + rs.getInt("id_aluno"));
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

            rs = ps.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getInt("id_usuario"),
                        rs.getString("matricula"),
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

    public Optional<Aluno> buscarPorIdAluno(int idAluno) {

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
            ps.setInt(1, idAluno);

            rs = ps.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getInt("id_usuario"),
                        rs.getString("matricula"),
                        rs.getTimestamp("dt_matricula")
                );

                return Optional.of(aluno);
            } else {

                    return Optional.empty();
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar aluno pelo id: " + idAluno);
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
            ps.setString(3, aluno.getMatricula());
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