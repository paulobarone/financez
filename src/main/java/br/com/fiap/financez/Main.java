package br.com.fiap.financez;

import br.com.fiap.financez.dao.*;
import br.com.fiap.financez.model.Transaction;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    UserDao userDao = new UserDao();
    AccountDao accountDao = new AccountDao();
    TransactionDao transactionDao = new TransactionDao();
    InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
    InvestmentDao investmentDao = new InvestmentDao();
  }
}