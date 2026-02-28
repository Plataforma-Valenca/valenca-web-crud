package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplinaDAO {

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
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return disciplina;
    }

    public Optional<Disciplina> buscarPorIdProfessor(int idProfessor) {

        String query = """
                SELECT nome FROM disciplinas
                WHERE id_professor = ?
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idProfessor);

            rs = ps.executeQuery();

            if (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome"),
                        rs.getInt("id_professor")
                );

                return Optional.of(disciplina);
            } else {
                throw new EntityNotFoundException("Disciplina", rs.getInt("id_disciplina"));
            }
        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao buscas disciplina: " + e.getMessage());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar aluno", e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
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
                        rs.getString("nome"),
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
    public List<Boletim> visualizarPorDisciplina(int idAluno, int idDisciplina) {
        String sql = """
        SELECT * FROM boletim
        WHERE id_aluno = ? AND id_disciplina = ?
    """;

        List<Boletim> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAluno);
            ps.setInt(2, idDisciplina);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Boletim boletim = new Boletim(
                        rs.getInt("id_boletim"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_disciplina"),
                        rs.getDouble("nota")
                );

                lista.add(boletim);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar boletim", e);
        }

        return lista;
    }
}