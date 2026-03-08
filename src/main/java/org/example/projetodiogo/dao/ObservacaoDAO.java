package org.example.projetodiogo.dao;

import org.example.projetodiogo.model.Observacao;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObservacaoDAO {

    // INSERT
    public boolean inserir(Observacao observacao) {

        String sql = """
                INSERT INTO observacoes
                (descricao, id_aluno, id_professor)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, observacao.getDescricao());
            stmt.setInt(2, observacao.getIdAluno());
            stmt.setInt(3, observacao.getIdProfessor());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir observação: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR TDAS AS OBSERVAÇÕES
    public ArrayList<Observacao> visualizarObservacoes() {

        String sql = "SELECT * FROM observacoes";

        ArrayList<Observacao> obsList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Observacao obs = new Observacao(
                        rs.getInt("id_observacao"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data_envio"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor")
                );
                obsList.add(obs);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar observação: " + e.getMessage());
        }
        return obsList;
    }


    // LISTAR POR ID ALUNO
    public ArrayList<Observacao> buscarPorIdAluno(int idAluno) {

        String sql = "SELECT * FROM observacoes WHERE id_aluno = ?";

        ArrayList<Observacao> obsList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idAluno);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Observacao obs = new Observacao(
                        rs.getInt("id_observacao"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data_envio"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor")
                );
                obsList.add(obs);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar observação: " + e.getMessage());
        }
        return obsList;
    }

    // LISTAR POR ID ALUNO E PROFESSOR
    public ArrayList<Observacao> buscarPorIdAlunoIdProfessor(int idAluno, int idProfessor) {

        String sql = "SELECT * FROM observacoes WHERE id_aluno = ? AND id_professor = ?";

        ArrayList<Observacao> obsList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idAluno);
            stmt.setInt(2, idProfessor);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Observacao obs = new Observacao(
                        rs.getInt("id_observacao"),
                        rs.getString("descricao"),
                        rs.getTimestamp("dt_envio"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor")
                );
                obsList.add(obs);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar observação: " + e.getMessage());
        }
        return obsList;
    }

    // UPDATE
    public boolean atualizar(Observacao observacao) {

        String sql = """
                UPDATE observacoes
                SET descricao = ?
                WHERE id_observacao = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, observacao.getDescricao());
            stmt.setInt(2, observacao.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar observação: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deletar(int id) {

        String sql = "DELETE FROM observacoes WHERE id_observacao = ?";

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar observação: " + e.getMessage());
            return false;
        }
    }
}
