package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.Investment;
import br.com.fiap.financez.model.InvestmentOption;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDao {
  private final Connection connection;

  public InvestmentDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(Investment[] investments) throws SQLException {
    connection.setAutoCommit(false);

    for (Investment investment : investments) {
      try {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO INVESTMENTS2 (ID_ACCOUNT2, ID_INVESTMENT_OPTION2, AMOUNT, DESCRIPTION) VALUES (?, ?, ?, ?)", new String[]{"id_investment2"});

        stm.setInt(1, investment.getAccount().getId());
        stm.setInt(2, investment.getInvestmentOption().getId());
        stm.setDouble(3, investment.getAmount());
        stm.setString(4, investment.getDescription());

        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
          investment.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }

        System.out.println("Investimento registrado com sucesso");
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        System.err.println("Não foi possível registrar o investimento: " + e.getMessage());
      }
    }
  }

  public Investment getInvestment(int id) throws SQLException {
    AccountDao accountDao = new AccountDao();
    InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();

    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM INVESTMENTS2 WHERE ID_INVESTMENT2 = ?");
      stm.setInt(1, id);

      ResultSet result = stm.executeQuery();
      if (result.next()) {
        int investmentId = result.getInt("id_investment2");
        int accountId = result.getInt("id_account2");
        int investmentOptionId = result.getInt("id_investment_option2");
        double amount = result.getDouble("amount");
        String description = result.getString("description");
        Timestamp createdAt = result.getTimestamp("created_at");

        Account account = accountDao.getAccount(accountId);
        InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(investmentOptionId);

        return new Investment(investmentId, account, investmentOption, amount, description, createdAt);
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar o investimento com ID " + id + ": " + e.getMessage());
      return null;
    }
  }

  public List<Investment> getInvestmentsAccount(Account account) throws SQLException {
    List<Investment> investments = new ArrayList<>();

    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM INVESTMENTS2 WHERE ID_ACCOUNT2 = ?");
      stm.setInt(1, account.getId());
      ResultSet result = stm.executeQuery();

      while (result.next()) {
        int investmentId = result.getInt("id_investment2");
        int accountId = result.getInt("id_account2");
        int investmentOptionId = result.getInt("id_investment_option2");
        double amount = result.getDouble("amount");
        String description = result.getString("description");
        Timestamp date = result.getTimestamp("created_at");

        AccountDao accountDao = new AccountDao();
        InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
        Account investmentAccount = accountDao.getAccount(accountId);
        InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(investmentOptionId);
        investments.add(new Investment(investmentId, investmentAccount, investmentOption, amount, description, date));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar os investimentos de " + account.getAccountHolder().getName() + ": " + e.getMessage());
    }

    return investments;
  }

  public List<Investment> getAll() throws SQLException {
    List<Investment> investments = new ArrayList<>();
    AccountDao accountDao = new AccountDao();
    InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();

    PreparedStatement stm = connection.prepareStatement("SELECT * FROM INVESTMENTS2");
    ResultSet result = stm.executeQuery();

    while (result.next()) {
      int investmentId = result.getInt("id_investment2");
      int accountId = result.getInt("id_account2");
      int investmentOptionId = result.getInt("id_investment_option2");
      double amount = result.getDouble("amount");
      String description = result.getString("description");
      Timestamp createdAt = result.getTimestamp("created_at");

      Account account = accountDao.getAccount(accountId);
      InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(investmentOptionId);

      investments.add(new Investment(investmentId, account, investmentOption, amount, description, createdAt));
    }

    return investments;
  }

  public void deleteInvestmentsAccount(Account account) throws SQLException {
    List<Investment> investments = getInvestmentsAccount(account);

    if (!investments.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM INVESTMENTS2 WHERE ID_ACCOUNT2 = ?");
        stm.setInt(1, account.getId());

        stm.executeUpdate();
        System.out.println("Investimentos de " + account.getAccountHolder().getName() + " deletados com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar os investimentos de " + account.getAccountHolder().getName() + ": " + e.getMessage());
      }
    } else {
      System.err.println(account.getAccountHolder().getName() + " não possui investimentos para serem deletados");
    }
  }

  public void deleteInvestment(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM INVESTMENTS2 WHERE ID_INVESTMENT2 = ?");
      stm.setInt(1, id);

      stm.executeUpdate();
      System.out.println("Investimento deletado com sucesso");
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar o investimento com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<Investment> investments = getAll();

    if (!investments.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM INVESTMENTS2");
        stm.executeUpdate();
        System.out.println("Investimentos deletados com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar os investimentos: " + e.getMessage());
      }
    } else {
      System.err.println("Não há investimentos para serem deletados");
    }
  }
}