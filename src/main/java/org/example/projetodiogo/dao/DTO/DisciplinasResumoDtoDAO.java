package org.example.projetodiogo.dao.DTO;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.DisciplinasResumoDTO;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class DisciplinasResumoDtoDAO {

    public ArrayList<DisciplinasResumoDTO> visualizarDisciplinasResumo() {

        String sql = """
                SELECT
                    d.id_disciplina,
                    d.nome,
                    p.id_professor,
                    u.nome AS professor_nome,
                    (SELECT COUNT(*) FROM turmas) AS quantidade_turmas,
                    0 AS media_geral
                FROM disciplinas d
                JOIN professores p ON d.id_professor = p.id_professor
                JOIN usuarios u ON p.id_usuario = u.id_usuario
                ORDER BY d.nome
                """;

        ArrayList<DisciplinasResumoDTO> lista = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                DisciplinasResumoDTO dto =
                        new DisciplinasResumoDTO(
                                rs.getInt("id_disciplina"),
                                rs.getString("nome"),
                                rs.getString("professor_nome"),
                                rs.getInt("quantidade_turmas"),
                                rs.getDouble("media_geral"),
                                rs.getInt("id_professor")
                        );

                lista.add(dto);
            }

            return lista;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar disciplinas", e);
        }
    }
}