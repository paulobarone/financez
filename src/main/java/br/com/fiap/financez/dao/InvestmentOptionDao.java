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

  public void register(InvestmentOption[] investmentOptions) throws SQLException {
    connection.setAutoCommit(false);

    for (InvestmentOption option : investmentOptions) {
      try {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO INVESTMENT_OPTIONS (NAME, RISK_LEVEL, RATE) VALUES (?, ?, ?)", new String[]{"id_investment_option"});

        stm.setString(1, option.getName());
        stm.setString(2, option.getRiskLevel().getDatabaseFormattedRisk());
        stm.setDouble(3, option.getRate());

        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
          option.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }

        System.out.println("Opção de investimento registrada com sucesso");
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        System.err.println("Não foi possível registrar a opção de investimento: " + e.getMessage());
      }
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

    return options;
  }

  public void deleteInvestmentOption(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM INVESTMENT_OPTIONS WHERE ID_INVESTMENT_OPTION = ?");
      stm.setInt(1, id);

      stm.executeUpdate();
      System.out.println("Opção de investimento deletada com sucesso");
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
        System.out.println("Opções de investimento deletadas com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar as opções de investimento: " + e.getMessage());
      }
    } else {
      System.err.println("Não há opções de investimento para serem deletadas");
    }
  }
}