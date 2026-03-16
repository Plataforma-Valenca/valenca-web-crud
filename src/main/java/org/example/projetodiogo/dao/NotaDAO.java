package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotaDAO {

    public void salvarOuAtualizarNota(int idAluno, int idDisciplina, Double n1, Double n2) {

        String sqlCheck = "SELECT id_nota FROM notas WHERE id_aluno = ? AND id_disciplina = ?";

        String sqlInsert = """
                INSERT INTO notas (id_aluno, id_disciplina, media1, media2, media_final)
                VALUES (?, ?, ?, ?, ?)
                """;

        String sqlUpdate = """
                UPDATE notas
                SET media1 = ?, media2 = ?, media_final = ?
                WHERE id_aluno = ? AND id_disciplina = ?
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();

            // Verifica se já existe registro para esse aluno + disciplina
            pstmt = conn.prepareStatement(sqlCheck);
            pstmt.setInt(1, idAluno);
            pstmt.setInt(2, idDisciplina);
            ResultSet rs = pstmt.executeQuery();

            Double mediaFinal = calcularMediaFinal(n1, n2);

            if (rs.next()) {
                // Já existe — atualiza
                pstmt = conn.prepareStatement(sqlUpdate);
                pstmt.setObject(1, n1);
                pstmt.setObject(2, n2);
                pstmt.setObject(3, mediaFinal);
                pstmt.setInt(4, idAluno);
                pstmt.setInt(5, idDisciplina);
            } else {
                // Não existe — insere
                pstmt = conn.prepareStatement(sqlInsert);
                pstmt.setInt(1, idAluno);
                pstmt.setInt(2, idDisciplina);
                pstmt.setObject(3, n1);
                pstmt.setObject(4, n2);
                pstmt.setObject(5, mediaFinal);
            }

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao salvar nota: " + e.getMessage());
            throw new DataAccessException("Erro ao salvar nota", e);
        } finally {
            try {
                if (conn  != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    private Double calcularMediaFinal(Double n1, Double n2) {
        if (n1 != null && n2 != null) return Math.round(((n1 + n2) / 2.0) * 100.0) / 100.0;
        if (n1 != null) return n1;
        if (n2 != null) return n2;
        return null;
    }

    public void editarNota(int idNota, Double n1, Double n2) {

        String sql = """
            UPDATE notas
            SET media1 = ?, media2 = ?, media_final = ?
            WHERE id_nota = ?
            """;

        Connection conn  = null;
        PreparedStatement pstmt = null;

        try {
            conn  = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setObject(1, n1);
            pstmt.setObject(2, n2);
            pstmt.setObject(3, calcularMediaFinal(n1, n2));
            pstmt.setInt(4, idNota);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao editar nota: " + e.getMessage());
            throw new DataAccessException("Erro ao editar nota", e);
        } finally {
            try {
                if (conn  != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}