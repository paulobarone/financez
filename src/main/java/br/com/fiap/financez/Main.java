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

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Deletando os dados já registrados no banco anteriormente" + ANSI_RESET);
    transactionDao.deleteAll();
    investmentDao.deleteAll();
    investmentOptionDao.deleteAll();
    accountDao.deleteAll();
    userDao.deleteAll();
  }
}