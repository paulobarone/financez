package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
  private final Connection connection;

  public AccountDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(Account account) throws SQLException {
    String sql = "INSERT INTO ACCOUNTS (ID_USER, BALANCE) VALUES (?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(sql, new String[]{"ID_ACCOUNT"})) {
      stm.setInt(1, account.getAccountHolder().getId());
      stm.setDouble(2, account.getBalance());
      stm.executeUpdate();
      try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          account.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível registrar a conta: " + e.getMessage());
    }
  }

  public Account getAccount(int id) throws SQLException {
    String sql = "SELECT * FROM ACCOUNTS WHERE ID_ACCOUNT = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int accountId = result.getInt("id_account");
          int accountHolderId = result.getInt("id_user");
          double balance = result.getDouble("balance");
          Timestamp createdAt = result.getTimestamp("created_at");
          UserDao userDao = new UserDao();
          User accountHolder = userDao.getUser(accountHolderId);

          return new Account(accountId, accountHolder, balance, createdAt);
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível coletar os dados da conta com ID " + id + ": " + e.getMessage());
    }
    return null;
  }

  public Account getAccountByUserId(int userId) throws SQLException {
    String sql = "SELECT * FROM ACCOUNTS WHERE ID_USER = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, userId);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int accountId = result.getInt("id_account");
          double balance = result.getDouble("balance");
          Timestamp createdAt = result.getTimestamp("created_at");
          UserDao userDao = new UserDao();
          User accountHolder = userDao.getUser(userId);
          return new Account(accountId, accountHolder, balance, createdAt);
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível coletar os dados da conta com ID do usuário " + userId + ": " + e.getMessage());
    }
    return null;
  }

  public List<Account> getAll() throws SQLException {
    List<Account> accounts = new ArrayList<>();
    UserDao userDao = new UserDao();
    String sql = "SELECT * FROM ACCOUNTS";
    try (PreparedStatement stm = connection.prepareStatement(sql);
         ResultSet result = stm.executeQuery()) {
      while (result.next()) {
        int accountId = result.getInt("id_account");
        int accountHolderId = result.getInt("id_user");
        double balance = result.getDouble("balance");
        Timestamp createdAt = result.getTimestamp("created_at");
        User accountHolder = userDao.getUser(accountHolderId);
        accounts.add(new Account(accountId, accountHolder, balance, createdAt));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as contas: " + e.getMessage());
    }
    return accounts;
  }

  public void deleteAccount(int id) throws SQLException {
    String sql = "DELETE FROM ACCOUNTS WHERE ID_ACCOUNT = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar a conta com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<Account> accounts = getAll();
    if (!accounts.isEmpty()) {
      String sql = "DELETE FROM ACCOUNTS";
      try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.executeUpdate();
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as contas: " + e.getMessage());
      }
    } else {
      System.err.println("Não há contas para serem deletadas");
    }
  }
}