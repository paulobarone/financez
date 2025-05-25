package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.Account;
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
    try {
      PreparedStatement stm = connection.prepareStatement("INSERT INTO TRANSACTIONS (ID_ACCOUNT, AMOUNT, ACTION, DESCRIPTION) VALUES (?, ?, ?, ?)", new String[]{"ID_TRANSACTION"});
      stm.setInt(1, transaction.getAccount().getId());
      stm.setDouble(2, transaction.getAmount());
      stm.setString(3, transaction.getAction().getDatabaseFormattedAction());
      stm.setString(4, transaction.getDescription());
      stm.executeUpdate();
      try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          transaction.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }
      }
      System.out.println("Transação registrada com sucesso");
    } catch (SQLException e) {
      System.err.println("Não foi possível registrar a transação: " + e.getMessage());
    }
  }

  public void registerAll(Transaction[] transactions) throws SQLException {
    connection.setAutoCommit(false);

    for (Transaction transaction : transactions) {
      try {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO TRANSACTIONS (ID_ACCOUNT, AMOUNT, ACTION, DESCRIPTION) VALUES (?, ?, ?, ?)", new String[]{"ID_TRANSACTION"});
        stm.setInt(1, transaction.getAccount().getId());
        stm.setDouble(2, transaction.getAmount());
        stm.setString(3, transaction.getAction().getDatabaseFormattedAction());
        stm.setString(4, transaction.getDescription());
        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
          transaction.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }
      } catch (SQLException e) {
        System.err.println("Não foi possível registrar a transação: " + e.getMessage());
      }
    }
  }

  public Transaction getTransaction(int id) throws SQLException {
    AccountDao accountDao = new AccountDao();

    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM TRANSACTIONS WHERE ID_TRANSACTION = ?");
      stm.setInt(1, id);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int transactionId = result.getInt("id_transaction");
          int accountId = result.getInt("id_account");
          double amount = result.getDouble("amount");
          String actionStr = result.getString("action");
          String description = result.getString("description");
          Timestamp createdAt = result.getTimestamp("created_at");
          TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
          Account account = accountDao.getAccount(accountId);
          return new Transaction(transactionId, account, amount, action, description, createdAt);
        } else {
          return null;
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar a transação com ID " + id + ": " + e.getMessage());
      return null;
    }
  }

  public List<Transaction> getTransactionsAccount(Account account) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM TRANSACTIONS WHERE ID_ACCOUNT = ? ORDER BY CREATED_AT DESC");
      stm.setInt(1, account.getId());

      ResultSet result = stm.executeQuery();
      while (result.next()) {
        int transactionId = result.getInt("id_transaction");
        int accountId = result.getInt("id_account");
        double amount = result.getDouble("amount");
        String actionStr = result.getString("action");
        String description = result.getString("description");
        Timestamp date = result.getTimestamp("created_at");
        AccountDao accountDao = new AccountDao();
        Account transactionAccount = accountDao.getAccount(accountId);
        TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
        transactions.add(new Transaction(transactionId, transactionAccount, amount, action, description, date));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as transações de " + account.getAccountHolder().getName() + ": " + e.getMessage());
    }
    return transactions;
  }

  public List<Transaction> getLastTransactions(Account account, int limit) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM TRANSACTIONS WHERE ID_ACCOUNT = ? ORDER BY CREATED_AT DESC FETCH FIRST ? ROWS ONLY");

      stm.setInt(1, account.getId());
      stm.setInt(2, limit);

      ResultSet result = stm.executeQuery();
      while (result.next()) {
        int transactionId = result.getInt("id_transaction");
        int accountId = result.getInt("id_account");
        double amount = result.getDouble("amount");
        String actionStr = result.getString("action");
        String description = result.getString("description");
        Timestamp date = result.getTimestamp("created_at");
        AccountDao accountDao = new AccountDao();
        Account transactionAccount = accountDao.getAccount(accountId);
        TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
        transactions.add(new Transaction(transactionId, transactionAccount, amount, action, description, date));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as transações de " + account.getAccountHolder().getName() + ": " + e.getMessage());
    }
    return transactions;
  }

  public List<Transaction> getAll() throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    AccountDao accountDao = new AccountDao();

    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM TRANSACTIONS");

      ResultSet result = stm.executeQuery();
      while (result.next()) {
        int transactionId = result.getInt("id_transaction");
        int accountId = result.getInt("id_account");
        double amount = result.getDouble("amount");
        String actionStr = result.getString("action");
        String description = result.getString("description");
        Timestamp createdAt = result.getTimestamp("created_at");
        TransactionAction action = TransactionAction.fromDatabaseFormattedAction(actionStr);
        Account account = accountDao.getAccount(accountId);
        transactions.add(new Transaction(transactionId, account, amount, action, description, createdAt));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as transações: " + e.getMessage());
    }
    return transactions;
  }

  public void deleteTransactionsAccount(Account account) throws SQLException {
    List<Transaction> transactions = getTransactionsAccount(account);
    if (!transactions.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM TRANSACTIONS WHERE ID_ACCOUNT = ?");
        stm.setInt(1, account.getId());
        stm.executeUpdate();
        System.out.println("Transações de " + account.getAccountHolder().getName() + " deletadas com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as transações de " + account.getAccountHolder().getName() + ": " + e.getMessage());
      }
    } else {
      System.err.println(account.getAccountHolder().getName() + " não possui transações para serem deletadas");
    }
  }

  public void deleteTransaction(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM TRANSACTIONS WHERE ID_TRANSACTION = ?");
      stm.setInt(1, id);
      stm.executeUpdate();
      System.out.println("Transação deletada com sucesso");
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar a transação com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<Transaction> transactions = getAll();
    if (!transactions.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM TRANSACTIONS");
        stm.executeUpdate();
        System.out.println("Transações deletadas com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as transações: " + e.getMessage());
      }
    } else {
      System.err.println("Não há transações para serem deletadas");
    }
  }
}