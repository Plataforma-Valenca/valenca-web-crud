package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoletimDAO {
    public ArrayList<Boletim> visualizarBoletim(int idAluno) {

        ArrayList<Boletim> lista = new ArrayList<>();

        String sql = """
        SELECT
            nome,
            media1,
            media2,
            media_final,
            CASE
                WHEN media_final >= 7 THEN 'APROVADO'
                WHEN media_final BETWEEN 5 AND 6.99 THEN 'RECUPERAÇÃO'
                ELSE 'REPROVADO'
                END AS situacao
        FROM (
                 SELECT
                     d.nome,
        
                     ROUND(COALESCE(AVG(CASE WHEN av.semestre = 1 THEN av.valor END), 0), 2) AS media1,
        
                     ROUND(COALESCE(AVG(CASE WHEN av.semestre = 2 THEN av.valor END), 0), 2) AS media2,
        
                     ROUND(
                             (
                                 COALESCE(AVG(CASE WHEN av.semestre = 1 THEN av.valor END), 0) +
                                 COALESCE(AVG(CASE WHEN av.semestre = 2 THEN av.valor END), 0)
                                 ) / 2.0
                         , 2) AS media_final
        
                 FROM disciplinas d
                          JOIN notas n ON n.id_disciplina = d.id_disciplina
                          LEFT JOIN avaliacoes av ON av.id_nota = n.id_nota
        
                 WHERE n.id_aluno = ?
        
                 GROUP BY d.nome
             ) sub
        ORDER BY nome;
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idAluno);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Boletim boletim = new Boletim(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome_disciplina"),
                        rs.getDouble("media1"),
                        rs.getDouble("media2"),
                        rs.getDouble("media_final")
                );
                lista.add(boletim);
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

        return lista;
    }

}
