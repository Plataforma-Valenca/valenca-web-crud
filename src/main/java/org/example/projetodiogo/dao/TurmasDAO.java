package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.EntityNotFoundException;
import org.example.projetodiogo.model.Turma;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TurmasDAO {
    public String buscarNomePorIdAluno(int idAluno) throws DataAccessException {

        String query = """
                SELECT t.nome FROM aluno_turma at
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
                        rs.getString("nome")
                );

                return turma.getNome();
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
}
