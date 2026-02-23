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

    // BUSCAR POR ID
    public List<Observacao> buscarPorIdAluno(int idAluno) {

        String sql = "SELECT * FROM observacoes WHERE id_aluno = ?";

        List<Observacao> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Observacao obs = new Observacao(
                        rs.getInt("id_observacao"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data_envio"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor")
                );

                return lista;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar observação: " + e.getMessage());
        }
        return lista;
    }

    // LISTAR POR ALUNO
    public List<Observacao> listarPorIdAluno(int idAluno) {

        String sql = "SELECT * FROM observacoes WHERE id_aluno = ?";

        List<Observacao> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Observacao(
                        rs.getInt("id_observacao"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_professor"),
                        rs.getString("descricao"),
                        rs.getTimestamp("dt_envio")
                ));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro ao listar observações: " + e.getMessage());
        }
        return lista;
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
