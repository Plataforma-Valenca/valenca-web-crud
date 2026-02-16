package org.example.projetodiogo.dao;

import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
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

}
