package br.com.fiap.financez.factory;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  static Dotenv dotenv = Dotenv.load();

  private static final String DB_URL = dotenv.get("DATABASE_URL");
  private static final String DB_USER = dotenv.get("DATABASE_USER");
  private static final String DB_PASS = dotenv.get("DATABASE_PASS");

  private static Connection currentConnection = null;

  public static Connection getConnection() throws SQLException {
    if (currentConnection == null || currentConnection.isClosed()) {
      try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        currentConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      } catch (ClassNotFoundException e) {
        System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage());
      }
    }

    return currentConnection;
  }

  public static void closeConnection() throws SQLException {
    if (currentConnection != null && !currentConnection.isClosed()) {
      currentConnection.close();
      currentConnection = null;
    }
  }
}