package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Turma;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmasDAO {
    public String buscarNomePorIdAluno(int idAluno) throws DataAccessException {
        String query = """
            SELECT t.nome
            FROM aluno_turma at
            JOIN turmas t ON at.id_turma = t.id_turma
            WHERE at.id_aluno = ?
            """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idAluno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            } else {
                throw new EntityNotFoundException("Turma não encontrada para idAluno: ", idAluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erro ao buscar turma", e);
        }
    }

    public Turma buscarPorIdAluno(int idAluno) throws DataAccessException {
        String query = """
                SELECT t.id_turma,t.nome,t.ano FROM aluno_turma at
                JOIN turma t ON at.turma_id = t.id
                WHERE id_aluno = ?
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idAluno);

            rs = ps.executeQuery();

            if (rs.next()) {
                Turma turma = new Turma(
                        rs.getInt("id_turma"),
                        rs.getString("nome"),
                        rs.getInt("ano")

                );

                return turma;
            } else {
                throw new EntityNotFoundException("Turma, idAluno: ", idAluno);
            }
        } catch (SQLException e) {
            System.out.println("[DAO] Erro ao buscar turma: " + e.getMessage());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar turma", e);
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

    public List<Turma> buscarTurmas() throws SQLException {

        String query = """
                SELECT * FROM turmas
                """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Turma> turmas = new ArrayList<>();

        try {
            conn = ConnectionFactory.conectar();
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Turma turma = new Turma(
                        rs.getInt("id_turma"),
                        rs.getString("nome"),
                        rs.getInt("ano")
                );
                turmas.add(turma);
            }
            return turmas;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar aluno pelo id: " + rs.getInt("id_aluno"));
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
    public void inserirTurma(String nome, int ano) {
        String sql = "INSERT INTO turmas (nome, ano) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, ano);

            stmt.executeUpdate();

            System.out.println("Turma inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editarTurma(int id,String nome,int ano){

        String sql = "UPDATE turmas SET nome=?, ano=? WHERE id_turma=?";

        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,nome);
            stmt.setInt(2,ano);
            stmt.setInt(3,id);

            stmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }public void deletarTurma(int id){

        String sql = "DELETE FROM turmas WHERE id_turma = ?";

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
