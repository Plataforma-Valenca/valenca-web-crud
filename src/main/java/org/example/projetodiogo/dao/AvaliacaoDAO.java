package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Avaliacao;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.ConnectionFactory;
import org.example.projetodiogo.util.HasherSenha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliacaoDAO {
    public void inserirAvaliacao(int idAluno, int idDisciplina, String descricao, double valor, int semestre) throws SQLException {
        String sqlNota = "INSERT INTO notas (id_aluno, id_disciplina) " +
                "VALUES (?, ?) ON CONFLICT (id_aluno, id_disciplina) DO NOTHING";

        String sqlPegarNota = "SELECT id_nota FROM notas WHERE id_aluno = ? AND id_disciplina = ?";

        String sqlInserirAvalicao = "INSERT INTO avaliacoes (id_nota, descricao, valor, semestre) " +
                "VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmtNota = null;
        PreparedStatement stmtPegarIdNota = null;
        PreparedStatement stmtInserirAvalicao = null;

        
        try {
            conn = ConnectionFactory.conectar();
            conn.setAutoCommit(false);
            
            stmtNota = conn.prepareStatement(sqlNota);
            stmtNota.setInt(1, idAluno);
            stmtNota.setInt(2, idDisciplina);
            stmtNota.executeUpdate();
            
            stmtPegarIdNota = conn.prepareStatement(sqlPegarNota);
            stmtPegarIdNota.setInt(1, idAluno);
            stmtPegarIdNota.setInt(2, idDisciplina);
            ResultSet rs = stmtPegarIdNota.executeQuery();

            int idNota = -1;
            if (rs.next()) {
                idNota = rs.getInt("id_nota");
            }

            stmtInserirAvalicao = conn.prepareStatement(sqlInserirAvalicao);
            stmtInserirAvalicao.setInt(1, idNota);
            stmtInserirAvalicao.setString(2, descricao);
            stmtInserirAvalicao.setDouble(3, valor);
            stmtInserirAvalicao.setInt(4, semestre);
            stmtInserirAvalicao.executeUpdate();

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
                WHERE  id_avaliacao = ?
                """;

        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, avaliacao.getDescricao());
            pstmt.setDouble(2, avaliacao.getValor());
            pstmt.setInt(3, avaliacao.getId());

            return (pstmt.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar avaliação: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao encerrar o banco de dados", e);
            }
        }
    }

}
