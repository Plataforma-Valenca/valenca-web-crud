package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    public List<Boletim> visualizarPorDisciplina(int idAluno) {

        List<Boletim> lista = new ArrayList<>();

        String sql = """
        SELECT
            d.nome,
        
            COALESCE(AVG(CASE WHEN av.semestre = 1 THEN av.valor END), 0) AS media1,
            COALESCE(AVG(CASE WHEN av.semestre = 2 THEN av.valor END), 0) AS media2,
        
            COALESCE(
                    ((
                        AVG(CASE WHEN av.semestre = 1 THEN av.valor END) +
                        AVG(CASE WHEN av.semestre = 2 THEN av.valor END)
                        ) / 2), 0
            ) AS media_final
        
        FROM disciplinas d
                 JOIN notas n ON n.id_disciplina = d.id_disciplina
                 LEFT JOIN avaliacoes av ON av.id_nota = n.id_nota
        
        WHERE n.id_aluno = ?
        
        GROUP BY d.nome
        ORDER BY d.nome
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
