package br.com.fiap.financez.factory;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static br.com.fiap.financez.util.ColorsANSI.*;

public class ConnectionFactory {
  static Dotenv dotenv = Dotenv.load();

  private static final String DB_URL = dotenv.get("DATABASE_URL");
  private static final String DB_USER = dotenv.get("DATABASE_USER");
  private static final String DB_PASS = dotenv.get("DATABASE_PASS");

  private static Connection currentConnection = null;

  public static Connection getConnection() throws SQLException {
    if (currentConnection == null || currentConnection.isClosed()) {
      currentConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      System.out.println(ANSI_GREEN + "---------- Nova conexão estabelecida ----------" + ANSI_RESET);
    }

    return currentConnection;
  }

  public static void closeConnection() throws SQLException {
    if (currentConnection != null && !currentConnection.isClosed()) {
      currentConnection.close();
      currentConnection = null;
      System.out.println(ANSI_RED + "---------- Conexão encerrada ----------" + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "---------- Nenhuma conexão ativa para encerrar ----------" + ANSI_RESET);
    }
  }
}