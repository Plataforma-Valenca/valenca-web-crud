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
                    u.nome AS nome_professor,
                    u.email,
                    u.cpf,
                    d.nome AS nome_disciplina
                FROM usuarios u
                JOIN professores p ON p.id_usuario = u.id_usuario
                JOIN disciplinas d ON d.id_professor = p.id_professor
                ORDER BY u.nome;
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ProfessorConsultaDTO> professoresList = new ArrayList<>();

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProfessorConsultaDTO professorConsultaDTO = new ProfessorConsultaDTO(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_professor"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("nome_disciplina")
                );

                professoresList.add(professorConsultaDTO);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por id do professor: ");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuário", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return professoresList;
    }

    public ArrayList<ProfessorConsultaDTO> buscarProfessoresFiltro(String busca) {

        String sql = """
        SELECT
                    u.id_usuario,
                    u.nome AS nome_professor,
                    u.email,
                    u.cpf,
                    d.nome AS nome_disciplina
                FROM usuarios u
                JOIN professores p ON p.id_usuario = u.id_usuario
                JOIN disciplinas d ON d.id_professor = p.id_professor
                WHERE u.nome LIKE ? OR email LIKE ? OR cpf LIKE ? OR d.nome LIKE ?
                ORDER BY u.nome;
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ProfessorConsultaDTO> professoresList = new ArrayList<>();

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            String filtro = "%" + busca + "%";

            pstmt.setString(1, filtro);
            pstmt.setString(2, filtro);
            pstmt.setString(3, filtro);
            pstmt.setString(4, filtro);

            rs = pstmt.executeQuery();

                while (rs.next()) {
                    ProfessorConsultaDTO professorConsultaDTO = new ProfessorConsultaDTO(
                            rs.getInt("id_usuario"),
                            rs.getString("nome_professor"),
                            rs.getString("email"),
                            rs.getString("cpf"),
                            rs.getString("nome_disciplina")
                    );
                professoresList.add(professorConsultaDTO);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por id do professor: ");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuário", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return professoresList;
    }
}
