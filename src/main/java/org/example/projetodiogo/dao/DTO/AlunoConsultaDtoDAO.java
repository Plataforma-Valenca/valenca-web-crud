package org.example.projetodiogo.dao.DTO;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.exceptions.InvalidCredentialsException;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoConsultaDtoDAO {
    public AlunoConsultaDTO buscarPorMatricula(Long matricula, int idTurma) {

        String sql = """
                SELECT
                    a.id_aluno,
                    u.nome,
                    u.cpf,
                    a.matricula,
                    t.nome AS turma
                
                FROM usuarios u
                JOIN alunos a ON a.id_usuario = u.id_usuario
                LEFT JOIN aluno_turma at ON at.id_aluno = a.id_aluno
                LEFT JOIN turmas t ON t.id_turma = at.id_turma
                
                WHERE a.matricula = ? AND t.id_turma = ?;
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, matricula);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                AlunoConsultaDTO consultaDTO = new AlunoConsultaDTO(
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getString("matricula"),
                        rs.getString("cpf"),
                        rs.getString("turma")
                );
                return consultaDTO;
            } else {
                throw new EntityNotFoundException("Usuario", matricula);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por matricula: " + matricula);
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
    }

    public ArrayList<AlunoConsultaDTO> buscarAlunos() {
        String sql = """
                SELECT
                    a.id_aluno,
                    u.nome,
                    u.cpf,
                    a.matricula,
                    t.nome AS turma
                
                FROM usuarios u
                         JOIN alunos a ON a.id_usuario = u.id_usuario
                         JOIN aluno_turma at ON at.id_aluno = a.id_aluno
                         JOIN turmas t ON t.id_turma = at.id_turma;
                """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<AlunoConsultaDTO> consultaDTOArrayList = new ArrayList<>();

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                AlunoConsultaDTO consultaDTO = new AlunoConsultaDTO(
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getString("matricula"),
                        rs.getString("cpf"),
                        rs.getString("turma")
                );

                consultaDTOArrayList.add(consultaDTO);
            }
            return consultaDTOArrayList;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar alunos");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar alunos", e);
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
    public List<AlunoConsultaDTO> buscarAlunosPorTurma(int idTurma) {

        String sql = """
        SELECT
            a.id_aluno,
            u.nome,
            u.cpf,
            a.matricula,
            t.nome AS turma
        FROM usuarios u
        JOIN alunos a ON a.id_usuario = u.id_usuario
        JOIN aluno_turma at ON at.id_aluno = a.id_aluno
        JOIN turmas t ON t.id_turma = at.id_turma
        WHERE t.id_turma = ?;
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<AlunoConsultaDTO> alunos = new ArrayList<>();

        try {

            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idTurma);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                AlunoConsultaDTO aluno = new AlunoConsultaDTO(
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getString("matricula"),
                        rs.getString("cpf"),
                        rs.getString("turma")
                );

                alunos.add(aluno);
            }

            return alunos;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar alunos da turma", e);

        } finally {

            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar conexão", e);
            }
        }
    }
    public AlunoConsultaDTO buscarPorCpf(String cpf) {

        if (cpf == null || cpf.isBlank()) {
            throw new InvalidCredentialsException();
        }

        String sql = """
        SELECT
            a.id_aluno,
            u.nome,
            u.cpf,
            a.matricula,
            t.nome AS turma
        FROM usuarios u
        JOIN alunos a ON a.id_usuario = u.id_usuario
        LEFT JOIN aluno_turma at ON at.id_aluno = a.id_aluno
        LEFT JOIN turmas t ON t.id_turma = at.id_turma
        WHERE u.cpf = ?;
        """;

        try (
                Connection conn = ConnectionFactory.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            pstmt.setString(1, cpf);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    String turma = rs.getString("turma");
                    if (turma == null) {
                        turma = "Sem turma";
                    }

                    return new AlunoConsultaDTO(
                            rs.getInt("id_aluno"),
                            rs.getString("nome"),
                            rs.getString("matricula"),
                            rs.getString("cpf"),
                            rs.getString("turma")
                    );
                } else {
                    throw new EntityNotFoundException("Usuario", cpf);
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar usuário por CPF", e);
        }
    }
}
