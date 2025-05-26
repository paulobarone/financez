package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.User;
import br.com.fiap.financez.model.Transaction;
import br.com.fiap.financez.model.enums.TransactionAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
  private final Connection connection;

  public TransactionDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(Transaction transaction) throws SQLException {
    String sql = "INSERT INTO TRANSACTIONS (ID_USER, AMOUNT, ACTION) VALUES (?, ?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(sql, new String[]{"ID_TRANSACTION"})) {
      stm.setInt(1, transaction.getUser().getId());
      stm.setDouble(2, transaction.getAmount());
      stm.setString(3, transaction.getAction().getDatabaseFormattedAction());
      stm.executeUpdate();
      try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          transaction.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível registrar a transação: " + e.getMessage());
    }
  }

  public void register(Transaction[] transactions) throws SQLException {
    for (Transaction transaction : transactions) {
      register(transaction);
    }
  }

  public Transaction getTransaction(int id) throws SQLException {
    UserDao userDao = new UserDao();
    String sql = "SELECT * FROM TRANSACTIONS WHERE ID_TRANSACTION = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int transactionId = result.getInt("id_transaction");
          int userId = result.getInt("id_user");
          double amount = result.getDouble("amount");
          String actionStr = result.getString("action");
          Timestamp createdAt = result.getTimestamp("created_at");
          TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
          User user = userDao.getUser(userId);
          return new Transaction(transactionId, user, amount, action, createdAt);
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar a transação com ID " + id + ": " + e.getMessage());
    }
    return null;
  }

  public List<Transaction> getTransactionsUser(User user) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    String sql = "SELECT * FROM TRANSACTIONS WHERE ID_USER = ? ORDER BY CREATED_AT DESC";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, user.getId());
      try (ResultSet result = stm.executeQuery()) {
        while (result.next()) {
          int transactionId = result.getInt("id_transaction");
          int userId = result.getInt("id_user");
          double amount = result.getDouble("amount");
          String actionStr = result.getString("action");
          Timestamp date = result.getTimestamp("created_at");
          UserDao userDao = new UserDao();
          User transactionUser = userDao.getUser(userId);
          TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
          transactions.add(new Transaction(transactionId, transactionUser, amount, action, date));
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as transações de " + user.getName() + ": " + e.getMessage());
    }
    return transactions;
  }

  public List<Transaction> getLastTransactions(User user, int limit) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    String sql = "SELECT * FROM TRANSACTIONS WHERE ID_USER = ? ORDER BY CREATED_AT DESC FETCH FIRST ? ROWS ONLY";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, user.getId());
      stm.setInt(2, limit);
      try (ResultSet result = stm.executeQuery()) {
        while (result.next()) {
          int transactionId = result.getInt("id_transaction");
          int userId = result.getInt("id_user");
          double amount = result.getDouble("amount");
          String actionStr = result.getString("action");
          Timestamp date = result.getTimestamp("created_at");
          UserDao userDao = new UserDao();
          User transactionUser = userDao.getUser(userId);
          TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
          transactions.add(new Transaction(transactionId, transactionUser, amount, action, date));
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as transações de " + user.getName() + ": " + e.getMessage());
    }
    return transactions;
  }

  public List<Transaction> getAll() throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    UserDao userDao = new UserDao();
    String sql = "SELECT * FROM TRANSACTIONS";
    try (PreparedStatement stm = connection.prepareStatement(sql);
         ResultSet result = stm.executeQuery()) {
      while (result.next()) {
        int transactionId = result.getInt("id_transaction");
        int userId = result.getInt("id_user");
        double amount = result.getDouble("amount");
        String actionStr = result.getString("action");
        Timestamp createdAt = result.getTimestamp("created_at");
        TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
        User user = userDao.getUser(userId);
        transactions.add(new Transaction(transactionId, user, amount, action, createdAt));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as transações: " + e.getMessage());
    }
    return transactions;
  }

  public void deleteTransactionsUser(User user) throws SQLException {
    List<Transaction> transactions = getTransactionsUser(user);
    if (!transactions.isEmpty()) {
      String sql = "DELETE FROM TRANSACTIONS WHERE ID_USER = ?";
      try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setInt(1, user.getId());
        stm.executeUpdate();
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as transações da conta de ID " + user.getId() + ": " + e.getMessage());
      }
    } else {
      System.err.println(user.getId() + " não possui transações para serem deletadas");
    }
  }

  public void deleteTransaction(int id) throws SQLException {
    String sql = "DELETE FROM TRANSACTIONS WHERE ID_TRANSACTION = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar a transação com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<Transaction> transactions = getAll();
    if (!transactions.isEmpty()) {
      String sql = "DELETE FROM TRANSACTIONS";
      try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.executeUpdate();
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as transações: " + e.getMessage());
      }
    } else {
      System.err.println("Não há transações para serem deletadas");
    }
  }
}