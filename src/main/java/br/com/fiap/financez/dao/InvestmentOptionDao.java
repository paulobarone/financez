package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.InvestmentOption;
import br.com.fiap.financez.model.enums.RiskLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestmentOptionDao {
  private final Connection connection;

  public InvestmentOptionDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(InvestmentOption investmentOption) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("INSERT INTO INVESTMENT_OPTIONS (NAME, RISK_LEVEL, RATE) VALUES (?, ?, ?)", new String[]{"ID_INVESTMENT_OPTION"});
      stm.setString(1, investmentOption.getName());
      stm.setString(2, investmentOption.getRiskLevel().getDatabaseFormattedRisk());
      stm.setDouble(3, investmentOption.getRate());
      stm.executeUpdate();
      ResultSet generatedKeys = stm.getGeneratedKeys();
      if (generatedKeys.next()) {
        investmentOption.setId(generatedKeys.getInt(1));
      } else {
        throw new SQLException("Erro ao encontrar ID");
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível registrar a opção de investimento: " + e.getMessage());
    }
  }

  public void register(InvestmentOption[] investmentOptions) throws SQLException {
    for(InvestmentOption investmentOption : investmentOptions) {
      register(investmentOption);
    }
  }

  public InvestmentOption getInvestmentOption(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM INVESTMENT_OPTIONS WHERE ID_INVESTMENT_OPTION = ?");
      stm.setInt(1, id);
      ResultSet result = stm.executeQuery();
      if (result.next()) {
        int optionId = result.getInt("id_investment_option");
        String name = result.getString("name");
        String riskLevelStr = result.getString("risk_level");
        double rate = result.getDouble("rate");
        RiskLevel riskLevel = RiskLevel.fromDatabaseFormattedRisk(riskLevelStr);
        return new InvestmentOption(optionId, name, riskLevel, rate);
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar a opção de investimento com ID " + id + ": " + e.getMessage());
      return null;
    }
  }

  public List<InvestmentOption> getAll() throws SQLException {
    List<InvestmentOption> options = new ArrayList<>();
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM INVESTMENT_OPTIONS");
      ResultSet result = stm.executeQuery();
      while (result.next()) {
        int optionId = result.getInt("id_investment_option");
        String name = result.getString("name");
        String riskLevelStr = result.getString("risk_level");
        double rate = result.getDouble("rate");
        RiskLevel riskLevel = RiskLevel.fromDatabaseFormattedRisk(riskLevelStr);
        options.add(new InvestmentOption(optionId, name, riskLevel, rate));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível encontrar as opções de investimento: " + e.getMessage());
      return null;
    }
    return options;
  }

  public void deleteInvestmentOption(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM INVESTMENT_OPTIONS WHERE ID_INVESTMENT_OPTION = ?");
      stm.setInt(1, id);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar a opção de investimento com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<InvestmentOption> options = getAll();
    if (!options.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM INVESTMENT_OPTIONS");
        stm.executeUpdate();
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as opções de investimento: " + e.getMessage());
      }
    } else {
      System.err.println("Não há opções de investimento para serem deletadas");
    }
  }
}