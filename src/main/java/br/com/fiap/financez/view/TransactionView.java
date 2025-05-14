package br.com.fiap.financez.view;

import br.com.fiap.financez.dao.TransactionDao;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.Transaction;

import java.sql.SQLException;
import java.util.List;

import static br.com.fiap.financez.util.ColorsANSI.ANSI_RESET;
import static br.com.fiap.financez.util.ColorsANSI.ANSI_BLUE;

import static br.com.fiap.financez.util.Format.getFormattedAmount;

public class TransactionView {
  public static void showAllTransactions() {
    try {
      TransactionDao transactionDao = new TransactionDao();
      List<Transaction> transactions = transactionDao.getAll();

      if (transactions.isEmpty()) {
        System.err.println("Nenhuma transação encontrada");
      } else {
        System.out.println("-> Lista de transações:");
        for (Transaction transaction : transactions) {
          System.out.println(ANSI_BLUE + "Transação" + ANSI_RESET + " ID: " + transaction.getId() + " | Conta: " + transaction.getAccount().getId() + " | Ação: " + transaction.getAction().getFormattedAction() + " | Valor: " + getFormattedAmount(transaction.getAmount()) + " | Descrição: " + transaction.getDescription() + " | Criado em: " + transaction.getCreatedAt());
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar transações: " + e.getMessage());
    }
  }

  public static void showTransactionsAccount(Account account) {
    try {
      TransactionDao transactionDao = new TransactionDao();
      List<Transaction> transactions = transactionDao.getTransactionsAccount(account);

      if (transactions.isEmpty()) {
        System.err.println("Nenhuma transação encontrada");
      } else {
        System.out.println("-> Lista de transações de " + account.getAccountHolder().getName() + ":");
        for (Transaction transaction : transactions) {
          System.out.println(ANSI_BLUE + "Transação" + ANSI_RESET + " ID: " + transaction.getId() + " | Valor: " + getFormattedAmount(transaction.getAmount()) + " | Ação: " + transaction.getAction().getFormattedAction() + " | Descrição: " + transaction.getDescription() + " | Data: " + transaction.getCreatedAt());
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar transações: " + e.getMessage());
    }
  }

  public static void showTransaction(int id) {
    try {
      TransactionDao transactionDao = new TransactionDao();
      Transaction transaction = transactionDao.getTransaction(id);

      if (transaction != null) {
        System.out.println(ANSI_BLUE + "Transação" + ANSI_RESET + " ID: " + transaction.getId() + " | Conta: " + transaction.getAccount().getId() + " | Ação: " + transaction.getAction().getFormattedAction() + " | Valor: " + getFormattedAmount(transaction.getAmount()) + " | Descrição: " + transaction.getDescription() + " | Criado em: " + transaction.getCreatedAt());
      } else {
        System.err.println("Transação com ID " + id + " não encontrada");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar transação com ID " + id + ": " + e.getMessage());
    }
  }
}