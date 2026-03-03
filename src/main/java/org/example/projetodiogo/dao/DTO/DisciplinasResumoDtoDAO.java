package org.example.projetodiogo.dao.DTO;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.DisciplinasResumoDTO;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisciplinasResumoDtoDAO {
    public ArrayList<DisciplinasResumoDTO> visualizarDisciplinasResumo() {
        String sql = """
                SELECT
                    d.id_disciplina,
                    d.nome,
                    u.nome AS professor_nome,
                    (SELECT COUNT(*) FROM turmas) AS quantidade_turmas,
                    COALESCE(media_disciplina.media_geral, 0) AS media_geral
                FROM disciplinas d
                         JOIN professores p ON d.id_professor = p.id_professor
                         JOIN usuarios u ON p.id_usuario = u.id_usuario
                
                         LEFT JOIN (
                    SELECT
                        medias.id_disciplina,
                        ROUND(AVG(medias.media_final), 2) AS media_geral
                    FROM (
                             SELECT
                                 n.id_disciplina,
                                 n.id_aluno,
                                 ROUND(
                                         (
                                             COALESCE(AVG(CASE WHEN av.semestre = 1 THEN av.valor END), 0) +
                                             COALESCE(AVG(CASE WHEN av.semestre = 2 THEN av.valor END), 0)
                                             ) / 2.0
                                     , 2) AS media_final
                             FROM notas n
                                      JOIN avaliacoes av ON av.id_nota = n.id_nota
                             GROUP BY n.id_disciplina, n.id_aluno
                         ) medias
                    GROUP BY medias.id_disciplina
                ) media_disciplina
                                   ON media_disciplina.id_disciplina = d.id_disciplina
                
                GROUP BY
                    d.id_disciplina,
                    d.nome,
                    u.nome,
                    media_disciplina.media_geral
                ORDER BY d.nome;
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<DisciplinasResumoDTO> resumoList = new ArrayList<>();

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                DisciplinasResumoDTO resumoDTO = new DisciplinasResumoDTO(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome"),
                        rs.getString("professor_nome"),
                        rs.getInt("quantidade_turmas"),
                        rs.getDouble("media_geral")
                );
                resumoList.add(resumoDTO);
            }
            return resumoList;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar disciplinas");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar disciplinas", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }
}