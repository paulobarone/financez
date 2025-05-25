package br.com.fiap.financez.view;

import br.com.fiap.financez.dao.AccountDao;
import br.com.fiap.financez.model.Account;

import java.sql.SQLException;
import java.util.List;

import static br.com.fiap.financez.util.ColorsANSI.ANSI_RESET;
import static br.com.fiap.financez.util.ColorsANSI.ANSI_YELLOW;

import static br.com.fiap.financez.util.Format.getFormattedAmount;

public class AccountView {
  public static void showAllAccounts() {
    try {
      AccountDao accountDao = new AccountDao();
      List<Account> accounts = accountDao.getAll();

      if (accounts.isEmpty()) {
        System.err.println("Nenhuma conta encontrada");
      } else {
        System.out.println("-> Lista de contas:");
        for (Account account : accounts) {
          System.out.println(ANSI_YELLOW + "Conta" + ANSI_RESET + " ID: " + account.getId() + " | Dono: " + account.getAccountHolder().getName() + " | Saldo: " + getFormattedAmount(account.getBalance()));
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar contas: " + e.getMessage());
    }
  }

  public static void showAccount(int id) {
    try {
      AccountDao accountDao = new AccountDao();
      Account account = accountDao.getAccount(id);

      if (account != null) {
        System.out.println(ANSI_YELLOW + "Conta" + ANSI_RESET + " ID: " + account.getId() + " | Dono: " + account.getAccountHolder().getName() + " | Saldo: " + getFormattedAmount(account.getBalance()));
      } else {
        System.err.println("Conta com ID " + id + " não encontrado");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar conta com ID " + id + ": " + e.getMessage());
    }
  }
}
