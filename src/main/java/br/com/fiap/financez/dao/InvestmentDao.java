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
    String sql = "INSERT INTO INVESTMENTS (ID_ACCOUNT, ID_INVESTMENT_OPTION, AMOUNT, DESCRIPTION) VALUES (?, ?, ?, ?)";
    for (Investment investment : investments) {
      try (PreparedStatement stm = connection.prepareStatement(sql, new String[]{"ID_INVESTMENT"})) {
        stm.setInt(1, investment.getAccount().getId());
        stm.setInt(2, investment.getInvestmentOption().getId());
        stm.setDouble(3, investment.getAmount());
        stm.setString(4, investment.getDescription());
        stm.executeUpdate();
        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            investment.setId(generatedKeys.getInt(1));
          } else {
            throw new SQLException("Erro ao encontrar ID");
          }
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
    String sql = "SELECT * FROM INVESTMENTS WHERE ID_INVESTMENT = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int investmentId = result.getInt("id_investment");
          int accountId = result.getInt("id_account");
          int investmentOptionId = result.getInt("id_investment_option");
          double amount = result.getDouble("amount");
          String description = result.getString("description");
          Timestamp createdAt = result.getTimestamp("created_at");
          Account account = accountDao.getAccount(accountId);
          InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(investmentOptionId);
          return new Investment(investmentId, account, investmentOption, amount, description, createdAt);
        } else {
          return null;
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar o investimento com ID " + id + ": " + e.getMessage());
      return null;
    }
  }

  public List<Investment> getInvestmentsAccount(Account account) throws SQLException {
    List<Investment> investments = new ArrayList<>();
    String sql = "SELECT * FROM INVESTMENTS WHERE ID_ACCOUNT = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, account.getId());
      try (ResultSet result = stm.executeQuery()) {
        while (result.next()) {
          int investmentId = result.getInt("id_investment");
          int accountId = result.getInt("id_account");
          int investmentOptionId = result.getInt("id_investment_option");
          double amount = result.getDouble("amount");
          String description = result.getString("description");
          Timestamp date = result.getTimestamp("created_at");
          AccountDao accountDao = new AccountDao();
          InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
          Account investmentAccount = accountDao.getAccount(accountId);
          InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(investmentOptionId);
          investments.add(new Investment(investmentId, investmentAccount, investmentOption, amount, description, date));
        }
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
    String sql = "SELECT * FROM INVESTMENTS";
    try (PreparedStatement stm = connection.prepareStatement(sql);
         ResultSet result = stm.executeQuery()) {
      while (result.next()) {
        int investmentId = result.getInt("id_investment");
        int accountId = result.getInt("id_account");
        int investmentOptionId = result.getInt("id_investment_option");
        double amount = result.getDouble("amount");
        String description = result.getString("description");
        Timestamp createdAt = result.getTimestamp("created_at");
        Account account = accountDao.getAccount(accountId);
        InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(investmentOptionId);
        investments.add(new Investment(investmentId, account, investmentOption, amount, description, createdAt));
      }
    }
    return investments;
  }

  public void deleteInvestmentsAccount(Account account) throws SQLException {
    List<Investment> investments = getInvestmentsAccount(account);
    if (!investments.isEmpty()) {
      String sql = "DELETE FROM INVESTMENTS WHERE ID_ACCOUNT = ?";
      try (PreparedStatement stm = connection.prepareStatement(sql)) {
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
    String sql = "DELETE FROM INVESTMENTS WHERE ID_INVESTMENT = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
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
      String sql = "DELETE FROM INVESTMENTS";
      try (PreparedStatement stm = connection.prepareStatement(sql)) {
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