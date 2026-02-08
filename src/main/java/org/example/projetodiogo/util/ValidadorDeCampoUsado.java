package org.example.projetodiogo.util;

import org.example.projetodiogo.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidadorDeCampoUsado {
    public static boolean ehCampoEmUso(String table, String field, String value) {
        String sql = "SELECT COUNT(*) FROM " + table+ " WHERE " + field + " = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try  {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, value);

            rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DataAccessException(" Erro ao verificar valor duplicado: ", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }
}
