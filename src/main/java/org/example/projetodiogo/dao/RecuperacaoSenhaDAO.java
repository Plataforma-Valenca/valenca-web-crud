package org.example.projetodiogo.dao;

import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;

public class RecuperacaoSenhaDAO {
    public void salvaToken(int idUsuario, String token, LocalDateTime expiracao) {

        String sql = """
        INSERT INTO recuperacao_senha (id_usuario, token, expiracao)
        VALUES (?, ?, ?)
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, token);
            pstmt.setTimestamp(3, Timestamp.valueOf(expiracao));
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public int validaToken(String token) {

        String sql = """
        SELECT id_usuario
        FROM recuperacao_senha
        WHERE token = ?
        AND expiracao > NOW()
        AND usado = false
    """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, token);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_usuario");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return -1;
    }

    public void marcarComoUsado(String token) {

        String sql = """
        UPDATE recuperacao_senha
        SET usado = true
        WHERE token = ?
    """;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, token);
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) ConnectionFactory.desconectar(conn);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }
}