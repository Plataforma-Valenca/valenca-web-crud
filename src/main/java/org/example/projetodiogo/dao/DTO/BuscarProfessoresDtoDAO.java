package org.example.projetodiogo.dao.DTO;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.ProfessorConsultaDTO;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarProfessoresDtoDAO {

    public ArrayList<ProfessorConsultaDTO> buscarProfessores() {

        String sql = """
                SELECT
                    u.id_usuario,
                    p.id_professor,
                    u.nome AS nome_professor,
                    u.email,
                    u.cpf,
                    u.username,
                    d.nome AS nome_disciplina
                FROM usuarios u
                JOIN professores p ON p.id_usuario = u.id_usuario
                JOIN disciplinas d ON d.id_professor = p.id_professor
                ORDER BY u.nome;
        """;

        ArrayList<ProfessorConsultaDTO> professoresList = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                ProfessorConsultaDTO professor = new ProfessorConsultaDTO(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_professor"),
                        rs.getString("nome_professor"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("username"),
                        rs.getString("nome_disciplina")
                );

                professoresList.add(professor);
            }

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar professores");
            e.printStackTrace();
            throw new DataAccessException("Erro ao buscar professores", e);
        }

        return professoresList;
    }

    public ArrayList<ProfessorConsultaDTO> buscarProfessoresFiltro(String busca) {

        String sql = """
                SELECT
                    u.id_usuario,
                    p.id_professor,
                    u.nome AS nome_professor,
                    u.email,
                    u.cpf,
                    u.username,
                    d.nome AS nome_disciplina
                FROM usuarios u
                JOIN professores p ON p.id_usuario = u.id_usuario
                JOIN disciplinas d ON d.id_professor = p.id_professor
                WHERE u.nome LIKE ? OR u.email LIKE ? OR u.cpf LIKE ? OR d.nome LIKE ?
                ORDER BY u.nome;
        """;

        ArrayList<ProfessorConsultaDTO> professoresList = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String filtro = "%" + busca + "%";

            pstmt.setString(1, filtro);
            pstmt.setString(2, filtro);
            pstmt.setString(3, filtro);
            pstmt.setString(4, filtro);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    ProfessorConsultaDTO professor = new ProfessorConsultaDTO(
                            rs.getInt("id_usuario"),
                            rs.getInt("id_professor"),
                            rs.getString("nome_professor"),
                            rs.getString("email"),
                            rs.getString("cpf"),
                            rs.getString("username"),
                            rs.getString("nome_disciplina")
                    );

                    professoresList.add(professor);
                }
            }

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar professores com filtro");
            e.printStackTrace();
            throw new DataAccessException("Erro ao buscar professores", e);
        }

        return professoresList;
    }
}