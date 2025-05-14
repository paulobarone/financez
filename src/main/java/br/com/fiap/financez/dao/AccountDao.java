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

  public void register(Account[] accounts) throws SQLException {
    connection.setAutoCommit(false);

    for (Account account : accounts) {
      try {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO ACCOUNTS2 (ID_USER2, ACCOUNT_NUMBER, AGENCY, BALANCE) VALUES (?, ?, ?, ?)", new String[]{"ID_ACCOUNT2"});

        stm.setInt(1, account.getAccountHolder().getId());
        stm.setString(2, account.getNumber());
        stm.setString(3, account.getAgency());
        stm.setDouble(4, account.getBalance());

        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
          account.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }

        System.out.println("Conta registrada com sucesso");
        connection.commit();
      } catch (SQLException e) {
        System.err.println("Não foi possível registrar a conta: " + e.getMessage());
      }
    }
  }

  public Account getAccount(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM ACCOUNTS2 WHERE ID_ACCOUNT2 = ?");
      stm.setInt(1, id);

      ResultSet result = stm.executeQuery();
      if (result.next()) {
        int accountId = result.getInt("id_account2");
        int accountHolderId = result.getInt("id_user2");
        String number = result.getString("account_number");
        String agency = result.getString("agency");
        double balance = result.getDouble("balance");
        Timestamp createdAt = result.getTimestamp("created_at");

        UserDao userDao = new UserDao();
        User accountHolder = userDao.getUser(accountHolderId);
        return new Account(accountId, accountHolder, number, agency, balance, createdAt);
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível coletar os dados da conta com ID " + id + ": " + e.getMessage());
      return null;
    }
  }

  public List<Account> getAll() throws SQLException {
    List<Account> accounts = new ArrayList<>();
    UserDao userDao = new UserDao();

    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM ACCOUNTS2");
      ResultSet result = stm.executeQuery();

      while (result.next()) {
        int accountId = result.getInt("id_account2");
        int accountHolderId = result.getInt("id_user2");
        String number = result.getString("account_number");
        String agency = result.getString("agency");
        double balance = result.getDouble("balance");
        Timestamp createdAt = result.getTimestamp("created_at");

        User accountHolder = userDao.getUser(accountHolderId);
        accounts.add(new Account(accountId, accountHolder, number, agency, balance, createdAt));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as contas: " + e.getMessage());
    }

    return accounts;
  }

  public void deleteAccount(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM ACCOUNTS2 WHERE ID_ACCOUNT2 = ?");

      stm.setInt(1, id);

      stm.executeUpdate();
      System.out.println("Conta deletada com sucesso");
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar a conta com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<Account> accounts = getAll();

    if (!accounts.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM ACCOUNTS2");
        stm.executeUpdate();
        System.out.println("Contas deletadas com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as contas: " + e.getMessage());
      }
    } else {
      System.err.println("Não há contas para serem deletadas");
    }
  }
}
