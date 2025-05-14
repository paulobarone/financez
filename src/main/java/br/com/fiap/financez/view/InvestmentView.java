package br.com.fiap.financez.view;

import br.com.fiap.financez.dao.InvestmentDao;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.Investment;

import java.sql.SQLException;
import java.util.List;

import static br.com.fiap.financez.util.ColorsANSI.ANSI_RESET;
import static br.com.fiap.financez.util.ColorsANSI.ANSI_ORANGE;

import static br.com.fiap.financez.util.Format.getFormattedAmount;

public class InvestmentView {
  public static void showAllInvestments() {
    try {
      InvestmentDao investmentDao = new InvestmentDao();
      List<Investment> investments = investmentDao.getAll();

      if (investments.isEmpty()) {
        System.err.println("Nenhum investimento encontrado");
      } else {
        System.out.println("-> Lista de investimentos:");
        for (Investment investment : investments) {
          System.out.println(ANSI_ORANGE + "Investimento" + ANSI_RESET + " ID: " + investment.getId() + " | Conta: " + investment.getAccount().getId() + " | Opção de Investimento: " + investment.getInvestmentOption().getName() + " | Valor: " + getFormattedAmount(investment.getAmount()) + " | Descrição: " + investment.getDescription() + " | Criado em: " + investment.getCreatedAt());
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar investimentos: " + e.getMessage());
    }
  }

  public static void showInvestmentsAccount(Account account) {
    try {
      InvestmentDao investmentDao = new InvestmentDao();
      List<Investment> investments = investmentDao.getInvestmentsAccount(account);

      if (investments.isEmpty()) {
        System.err.println("Nenhum investimento encontrado");
      } else {
        System.out.println("-> Lista de investimentos de " + account.getAccountHolder().getName() + ":");
        for (Investment investment : investments) {
          System.out.println(ANSI_ORANGE + "Investimento" + ANSI_RESET + " ID: " + investment.getId() + " | Valor: " + getFormattedAmount(investment.getAmount()) + " | Descrição: " + investment.getDescription() + " | Data: " + investment.getCreatedAt());
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar investimentos: " + e.getMessage());
    }
  }

  public static void showInvestment(int id) {
    try {
      InvestmentDao investmentDao = new InvestmentDao();
      Investment investment = investmentDao.getInvestment(id);

      if (investment != null) {
        System.out.println(ANSI_ORANGE + "Investimento" + ANSI_RESET + " ID: " + investment.getId() + " | Conta: " + investment.getAccount().getId() + " | Opção de Investimento: " + investment.getInvestmentOption().getName() + " | Valor: " + getFormattedAmount(investment.getAmount()) + " | Descrição: " + investment.getDescription() + " | Criado em: " + investment.getCreatedAt());
      } else {
        System.err.println("Investimento com ID " + id + " não encontrado");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar investimento com ID " + id + ": " + e.getMessage());
    }
  }
}