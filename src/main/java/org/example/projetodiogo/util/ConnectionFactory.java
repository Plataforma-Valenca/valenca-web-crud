package org.example.projetodiogo.util;

import org.example.projetodiogo.exceptions.DatabaseConnectionException;

import javax.naming.ConfigurationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";

    /**
     * Método para estabelecer conexão com o banco de dados com base no Driver e informações de
     * Host do banco
     *
     * @return Connection - Retorna a conexão estabelecida
     * @throws DatabaseConnectionException se falhar ao conectar
     * @throws ConfigurationException se variáveis de ambiente não estiverem configuradas
     * @author enzomota-ieg
     */

    public static Connection conectar() {
        try {

            final String URL = System.getenv("DB_URL");
            final String USER = System.getenv("DB_USER");
            final String PASSWORD = System.getenv("DB_PASSWORD");

            if (URL == null || URL.isEmpty()) {
                throw new ConfigurationException(
                        "A variável de ambiente DB_URL não está configurada." +
                                "Configure as credenciais do banco corretamente."
                );
            }

            if (USER == null || USER.isEmpty()) {
                throw new ConfigurationException(
                        "A variável de ambiente DB_USER não está configurada." +
                                "Configure as credenciais do banco corretamente."
                );
            }

            if (PASSWORD == null) {
                throw new ConfigurationException(
                        "A variável de ambiente DB_PASSWORD não está configurada." +
                                "Configure as credenciais do banco corretamente."
                );
            }

            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                throw new ConfigurationException(
                        "O Driver do banco dados não foi encontrado. Confira se a dependência do postgresql está no pom.xml"
                );
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn == null) {
                System.err.println("A conexão retornou null");
                throw new DatabaseConnectionException(
                        "Conexão retornou null",
                        new SQLException("Connection is null")
                );
            }

            return conn;

        } catch (SQLException | ConfigurationException sqle) {
            System.err.println("[ERROR DB] Falha ao concetar com o banco de dados: " + sqle);
        }

        return null;
    }

    /**
     * Método para fechar a conexão com o banco de dados
     *
     * @param conn Conexão a ser fechada
     * @author enzomota-ieg
     */

    public static void desconectar(Connection conn) {
        if (conn == null) {
            return;
        }

        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqle) {
            System.err.println("[DB ERROR] Erro ao fechar conexão");
            sqle.printStackTrace(System.err);
        }
    }
}