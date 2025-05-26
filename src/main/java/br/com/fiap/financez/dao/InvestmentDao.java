package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.Investment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDao {
  private final Connection connection;

  public InvestmentDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(Investment investment) throws SQLException {
    String sql = "INSERT INTO INVESTMENTS (ID_ACCOUNT, ID_INVESTMENT_OPTION, AMOUNT) VALUES (?, ?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(sql, new String[]{"ID_INVESTMENT"})) {
      stm.setInt(1, investment.getAccountId());
      stm.setInt(2, investment.getInvestmentOptionId());
      stm.setDouble(3, investment.getAmount());
      stm.executeUpdate();
      try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          investment.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível registrar o investimento: " + e.getMessage());
    }
  }

  public Investment getInvestment(int id) throws SQLException {
    String sql = "SELECT * FROM INVESTMENTS WHERE ID_INVESTMENT = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int investmentId = result.getInt("id_investment");
          int accountId = result.getInt("id_account");
          int investmentOptionId = result.getInt("id_investment_option");
          double amount = result.getDouble("amount");
          Timestamp createdAt = result.getTimestamp("created_at");

          return new Investment(investmentId, accountId, investmentOptionId, amount, createdAt);
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar o investimento com ID " + id + ": " + e.getMessage());
    }
    return null;
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
          Timestamp date = result.getTimestamp("created_at");

          investments.add(new Investment(investmentId, accountId, investmentOptionId, amount, date));
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar os investimentos de " + account.getAccountHolder().getName() + ": " + e.getMessage());
    }
    return investments;
  }

  public List<Investment> getAll() throws SQLException {
    List<Investment> investments = new ArrayList<>();
    String sql = "SELECT * FROM INVESTMENTS";
    try (PreparedStatement stm = connection.prepareStatement(sql);
         ResultSet result = stm.executeQuery()) {
      while (result.next()) {
        int investmentId = result.getInt("id_investment");
        int accountId = result.getInt("id_account");
        int investmentOptionId = result.getInt("id_investment_option");
        double amount = result.getDouble("amount");
        Timestamp createdAt = result.getTimestamp("created_at");

        investments.add(new Investment(investmentId, accountId, investmentOptionId, amount, createdAt));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar os investimentos: " + e.getMessage());
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
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar os investimentos: " + e.getMessage());
      }
    } else {
      System.err.println("Não há investimentos para serem deletados");
    }
  }
}