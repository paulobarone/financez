package br.com.fiap.financez;

import br.com.fiap.financez.dao.*;
import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.*;
import br.com.fiap.financez.model.enums.Gender;
import br.com.fiap.financez.model.enums.RiskLevel;
import br.com.fiap.financez.model.enums.TransactionAction;
import br.com.fiap.financez.view.*;

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

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Cria e registra os dados dos usuários" + ANSI_RESET);
    User user1 = new User("Ana Silva", "ana.silva@gmail.com", "senha123", "4567890", "123.456.789-01", Gender.FEMALE);
    User user2 = new User("Carlos Oliveira", "carlos.oliveira@hotmail.com", "abcde", "9876543", "987.654.321-02", Gender.MALE);
    User user3 = new User("Mariana Souza", "mariana@yahoo.com", "securepass", "2345678", "555.444.333-10", Gender.FEMALE);
    User user4 = new User("Ricardo Alves", "ricardo.a@gmail.com", "mysecret", "7654321", "111.222.333-44", Gender.MALE);
    User user5 = new User("Juliana Pereira", "juli.pereira@outlook.com", "qwerty", "8901234", "777.888.999-22", Gender.FEMALE);
    userDao.register(new User[]{user1, user2, user3, user4, user5});

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Cria e registra os dados das contas dos usuários" + ANSI_RESET);
    Account account1 = new Account(user1, "15843", "157-8", 485.25);
    Account account2 = new Account(user2, "84354", "349-3", 526.65);
    Account account3 = new Account(user3, "44982", "751-6", 123.94);
    Account account4 = new Account(user4, "87451", "212-8", 343.84);
    Account account5 = new Account(user5, "51158", "549-4", 6434.52);
    accountDao.register(new Account[]{account1, account2, account3, account4, account5});

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "Cria e registra as transações das contas dos usuários" + ANSI_RESET);
    Transaction transaction1 = new Transaction(account1, 150.5, TransactionAction.EXPENSE, "Aluguel");
    Transaction transaction2 = new Transaction(account1, 500.00, TransactionAction.INCOME);
    Transaction transaction3 = new Transaction(account2, 95.15, TransactionAction.EXPENSE);
    Transaction transaction4 = new Transaction(account2, 1000.00, TransactionAction.INCOME, "Salário");
    Transaction transaction5 = new Transaction(account4, 750.00, TransactionAction.EXPENSE);
    Transaction transaction6 = new Transaction(account5, 123.45, TransactionAction.INCOME, "Empréstimo");
    transactionDao.register(new Transaction[]{transaction1, transaction2, transaction3, transaction4, transaction5, transaction6});

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "Cria e registra as opções de investimento" + ANSI_RESET);
    InvestmentOption option1 = new InvestmentOption("CDB", RiskLevel.LOW, 0.15);
    InvestmentOption option2 = new InvestmentOption("Tesouro Direto", RiskLevel.MEDIUM, 0.20);
    InvestmentOption option3 = new InvestmentOption("Imobiliário", RiskLevel.HIGH, 0.3);
    investmentOptionDao.register(new InvestmentOption[]{option1, option2, option3});

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "Cria e registra os investimentos das contas dos usuários" + ANSI_RESET);
    Investment investment1 = new Investment(account1, option1, 225);
    Investment investment2 = new Investment(account1, option3, 333, "Investimento para o carro");
    Investment investment3 = new Investment(account2, option2, 500, "Investimento para o custo da faculdade");
    Investment investment4 = new Investment(account2, option2, 750);
    Investment investment5 = new Investment(account4, option1, 220, "Investimento para mudança");
    Investment investment6 = new Investment(account5, option3, 150);
    investmentDao.register(new Investment[]{investment1, investment2, investment3, investment4, investment5, investment6});

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Mostra todos os usuários, contas, transações, opções de investimentos e investimentos" + ANSI_RESET);
    UserView.showAllUsers();
    System.out.println(" ");
    AccountView.showAllAccounts();
    System.out.println(" ");
    TransactionView.showAllTransactions();
    System.out.println(" ");
    InvestmentOptionView.showAllInvestmentOptions();
    System.out.println(" ");
    InvestmentView.showAllInvestments();

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Deleta um usuário, sua conta, transações, e investimentos" + ANSI_RESET);
    investmentDao.deleteInvestmentsAccount(account1);
    transactionDao.deleteTransactionsAccount(account1);
    accountDao.deleteAccount(account1.getId());
    userDao.deleteUser(user1.getId());

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Mostra todos os usuários, contas, transações, opções de investimentos e investimentos novamente" + ANSI_RESET);
    UserView.showAllUsers();
    System.out.println(" ");
    AccountView.showAllAccounts();
    System.out.println(" ");
    TransactionView.showAllTransactions();
    System.out.println(" ");
    InvestmentOptionView.showAllInvestmentOptions();
    System.out.println(" ");
    InvestmentView.showAllInvestments();

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Mostra o usuário, a conta, suas transações e investimentos" + ANSI_RESET);
    UserView.showUser(user2.getId());
    AccountView.showAccount(account2.getId());
    System.out.println(" ");
    InvestmentView.showInvestmentsAccount(account2);

    System.out.println(" ");

    System.out.println(ANSI_CYAN + "- Mostra apenas uma transação e um investimento" + ANSI_RESET);
    TransactionView.showTransaction(transaction3.getId());
    InvestmentView.showInvestment(investment3.getId());

    System.out.println(" ");

    ConnectionFactory.closeConnection();
  }
}