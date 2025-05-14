package br.com.fiap.financez.view;

import br.com.fiap.financez.dao.InvestmentOptionDao;
import br.com.fiap.financez.model.InvestmentOption;

import java.sql.SQLException;
import java.util.List;

import static br.com.fiap.financez.util.ColorsANSI.ANSI_RESET;
import static br.com.fiap.financez.util.ColorsANSI.ANSI_ORANGE;

import static br.com.fiap.financez.util.Format.getFormattedRate;

public class InvestmentOptionView {
  public static void showAllInvestmentOptions() {
    try {
      InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
      List<InvestmentOption> investmentOptions = investmentOptionDao.getAll();

      if (investmentOptions.isEmpty()) {
        System.err.println("Nenhuma opção de investimento encontrada");
      } else {
        System.out.println("-> Lista de opções de investimento:");
        for (InvestmentOption investmentOption : investmentOptions) {
          System.out.println(ANSI_ORANGE + "Opção de Investimento" + ANSI_RESET + " ID: " + investmentOption.getId() + " | Nome: " + investmentOption.getName() + " | Nível de Risco: " + investmentOption.getRiskLevel().getFormattedRisk() + " | Taxa: " + getFormattedRate(investmentOption.getRate()));
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar opções de investimento: " + e.getMessage());
    }
  }

  public static void showInvestmentOption(int id) {
    try {
      InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
      InvestmentOption investmentOption = investmentOptionDao.getInvestmentOption(id);

      if (investmentOption != null) {
        System.out.println(ANSI_ORANGE + "Opção de Investimento" + ANSI_RESET + " ID: " + investmentOption.getId() + " | Nome: " + investmentOption.getName() + " | Nível de Risco: " + investmentOption.getRiskLevel().getFormattedRisk() + " | Taxa: " + getFormattedRate(investmentOption.getRate()));
      } else {
        System.err.println("Opção de investimento com ID " + id + " não encontrada");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar opção de investimento com ID " + id + ": " + e.getMessage());
    }
  }
}