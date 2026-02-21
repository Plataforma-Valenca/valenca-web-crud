package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    public Boletim visualizarPorDisciplina(int idAluno, int idDisciplina) {

        String sql = """
        SELECT
            d.nome,
        
            ROUND(COALESCE(AVG(CASE WHEN av.semestre = 1 THEN av.valor END), 0), 2) AS media1,
            ROUND(COALESCE(AVG(CASE WHEN av.semestre = 2 THEN av.valor END), 0), 2) AS media2,
        
            ROUND(
        		COALESCE(
                    ((
                         ROUND(COALESCE(AVG(CASE WHEN av.semestre = 1 THEN av.valor END), 0), 2) +
                         ROUND(COALESCE(AVG(CASE WHEN av.semestre = 2 THEN av.valor END), 0), 2)
        			 ) / 2), 0
            )
        	, 2) AS media_final
        
        FROM disciplinas d
                 JOIN notas n ON n.id_disciplina = d.id_disciplina
                 LEFT JOIN avaliacoes av ON av.id_nota = n.id_nota
        
        WHERE n.id_aluno = ? AND d.id_disciplina = ?
        
        GROUP BY d.nome
        ORDER BY d.nome;
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idAluno);
            pstmt.setInt(2, idDisciplina);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Boletim boletim = new Boletim(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome_disciplina"),
                        rs.getDouble("media1"),
                        rs.getDouble("media2"),
                        rs.getDouble("media_final")
                );
                return boletim;
            }

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao visualizar o boletim: " + e.getMessage());
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }
        return null;
    }

    public Disciplina buscarPorId(int idDisciplina) {

        String sql = """
                SELECT nome FROM disciplinas
                WHERE id_disciplina = ?
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        Disciplina disciplina = new Disciplina();

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idDisciplina);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                disciplina.setNome(rs.getString("nome"));
                return disciplina;
            }

        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao visualizar o boletim: " + e.getMessage());
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }
        return disciplina;
    }

    public ArrayList<Disciplina> visualizarDisciplinas() {

        String sql = "SELECT * FROM disciplinas";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Disciplina> disciplinasList = new ArrayList<>();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome_disciplina"),
                        rs.getInt("id_professor")
                );

                disciplinasList.add(disciplina);
            }
            return disciplinasList;
        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao visualizar o boletim: " + e.getMessage());
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
            }
        }
        return null;
    }
}