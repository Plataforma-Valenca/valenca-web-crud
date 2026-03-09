package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Avaliacao;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.*;

public class AvaliacaoDAO {

    public void inserirAvaliacao(int idAluno, int idDisciplina, String descricao, double valor, int semestre) throws SQLException {

        String sqlNota = """
        INSERT INTO notas (id_aluno, id_disciplina)
        VALUES (?, ?)
        ON CONFLICT (id_aluno, id_disciplina) DO NOTHING
        """;

        String sqlPegarNota = """
        SELECT id_nota FROM notas
        WHERE id_aluno = ? AND id_disciplina = ?
        """;

        String sqlInserirAvaliacao = """
        INSERT INTO avaliacoes (id_nota, descricao, valor, semestre)
        VALUES (?, ?, ?, ?)
        """;

        Connection conn = null;

        try {

            conn = ConnectionFactory.conectar();
            conn.setAutoCommit(false);

            try (PreparedStatement stmtNota = conn.prepareStatement(sqlNota)) {

                stmtNota.setInt(1, idAluno);
                stmtNota.setInt(2, idDisciplina);
                stmtNota.executeUpdate();
            }

            int idNota = -1;

            try (PreparedStatement stmtPegarNota = conn.prepareStatement(sqlPegarNota)) {

                stmtPegarNota.setInt(1, idAluno);
                stmtPegarNota.setInt(2, idDisciplina);

                ResultSet rs = stmtPegarNota.executeQuery();

                if (rs.next()) {
                    idNota = rs.getInt("id_nota");
                }

                rs.close();
            }

            if (idNota == -1) {
                throw new SQLException("Nota não encontrada.");
            }

            try (PreparedStatement stmtInserirAvaliacao = conn.prepareStatement(sqlInserirAvaliacao)) {

                stmtInserirAvaliacao.setInt(1, idNota);
                stmtInserirAvaliacao.setString(2, descricao);
                stmtInserirAvaliacao.setDouble(3, valor);
                stmtInserirAvaliacao.setInt(4, semestre);

                stmtInserirAvaliacao.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {

            if (conn != null) conn.rollback();
            throw e;

        } finally {

            if (conn != null) conn.close();
        }
    }

    public boolean atualizar(Avaliacao avaliacao) {

        String sql = """
        UPDATE avaliacoes
        SET descricao = ?, valor = ?, semestre = ?
        WHERE id_avaliacao = ?
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, avaliacao.getDescricao());
            pstmt.setDouble(2, avaliacao.getValor());
            pstmt.setInt(3, avaliacao.getSemestre());
            pstmt.setInt(4, avaliacao.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar avaliação: " + e.getMessage());
            return false;

        } finally {

            try {

                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectionFactory.desconectar(conn);

            } catch (SQLException e) {

                throw new DataAccessException("Erro ao encerrar o banco", e);
            }
        }
    }
}