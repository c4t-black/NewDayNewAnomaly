package com.mycompany.newdaynewanomaly.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de conexões JDBC com o PostgreSQL.
 *
 * <p>Configuração padrão:
 * <ul>
 *   <li>Host: localhost</li>
 *   <li>Porta: 5432</li>
 *   <li>Banco: newdaynewanomaly</li>
 *   <li>Schema: game</li>
 *   <li>Usuário: postgres</li>
 *   <li>Senha: postgres</li>
 * </ul>
 * Ajuste as constantes abaixo conforme o seu ambiente.
 */
public class ConnectionFactory {

    private static final String HOST     = "localhost";
    private static final int    PORT     = 5432;
    private static final String DATABASE = "newdaynewanomaly";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String URL =
            "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE
            + "?currentSchema=game"
            + "&useUnicode=true"
            + "&characterEncoding=UTF-8";

    /** Construtor privado – classe utilitária, sem instâncias. */
    private ConnectionFactory() {}

    /**
     * Abre e retorna uma nova conexão com o banco.
     *
     * @return {@link Connection} pronta para uso
     * @throws SQLException se a conexão falhar
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Fecha silenciosamente uma conexão (ignora {@code null}).
     * Útil em blocos {@code finally}.
     *
     * @param connection conexão a fechar (pode ser {@code null})
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("[ConnectionFactory] Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
