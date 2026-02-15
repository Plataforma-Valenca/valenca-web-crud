package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Avaliacao;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.ConnectionFactory;
import org.example.projetodiogo.util.HasherSenha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvaliacaoDAO {
    public boolean inserirAvaliacao(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacoes(id_nota, descricao, valor) VALUES(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, avaliacao.getIdNota());
            pstmt.setString(2, avaliacao.getDescricao());
            pstmt.setDouble(3, avaliacao.getValor());

            return (pstmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.err.println("[DAO] Erro ao inserir avalaicao: " + avaliacao);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao inserir avalaicao", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectionFactory.desconectar(conn);
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao encerrar o banco de dados", e);
            }
        }
    }

    public boolean atualizar(Avaliacao avaliacao) {

        String sql = """
                UPDATE avaliacoes
                SET descricao = ?, valor = ?
                WHERE  id_avaliacao = ?
                """;

        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, avaliacao.getDescricao());
            pstmt.setDouble(2, avaliacao.getValor());
            pstmt.setInt(3, avaliacao.getId());

            return (pstmt.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar avaliação: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao encerrar o banco de dados", e);
            }
        }
    }

}
