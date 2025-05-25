package br.com.fiap.financez;

import br.com.fiap.financez.dao.*;

import java.sql.SQLException;

import static br.com.fiap.financez.util.ColorsANSI.ANSI_CYAN;
import static br.com.fiap.financez.util.ColorsANSI.ANSI_RESET;

public class Main {
  public static void main(String[] args) throws SQLException {
    UserDao userDao = new UserDao();
    AccountDao accountDao = new AccountDao();
    TransactionDao transactionDao = new TransactionDao();
    InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
    InvestmentDao investmentDao = new InvestmentDao();
  }
}