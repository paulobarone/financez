package br.com.fiap.financez.service;

import br.com.fiap.financez.dao.AccountDao;
import br.com.fiap.financez.dao.TransactionDao;
import br.com.fiap.financez.dao.UserDao;
import br.com.fiap.financez.exception.*;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.Transaction;
import br.com.fiap.financez.model.User;
import br.com.fiap.financez.model.enums.TransactionAction;

import java.sql.SQLException;
import java.util.regex.Pattern;

import static br.com.fiap.financez.exception.UserRegistrationException.Reason.EMAIL_INVALID;
import static br.com.fiap.financez.exception.UserRegistrationException.Reason.EMPRY_FIELDS;

public class UserService {
  private final UserDao userDao;
  private final AccountDao accountDao;
  private final TransactionDao transactionDao;

  public UserService() throws SQLException {
    this.userDao = new UserDao();
    this.accountDao = new AccountDao();
    this.transactionDao = new TransactionDao();
  }

  public void registerUser(String name, String email, String password, String cpf) throws SQLException {
    if (name == null || name.trim().isEmpty() ||
        email == null || email.trim().isEmpty() ||
        password == null || password.isEmpty() ||
        cpf == null || cpf.trim().isEmpty()) {
      throw new UserRegistrationException(EMPRY_FIELDS, "Todos os campos obrigatórios devem ser preenchidos");
    }

    if (!isValidEmail(email)) {
      throw new UserRegistrationException(EMAIL_INVALID, "O formato do e-mail é inválido");
    }

    User newUser = new User(name, email, password, cpf);
    userDao.register(newUser);

    Account newAccount = new Account(newUser, 200.00);
    accountDao.register(newAccount);

    Transaction t1 = new Transaction(newAccount, 750.00, TransactionAction.INCOME, "Salário recebido");
    Transaction t2 = new Transaction(newAccount, 45.90, TransactionAction.EXPENSE, "Compra no supermercado");
    Transaction t3 = new Transaction(newAccount, 22.50, TransactionAction.EXPENSE, "Assinatura de streaming");
    Transaction t4 = new Transaction(newAccount, 200.00, TransactionAction.INCOME, "Venda de itens usados");
    Transaction t5 = new Transaction(newAccount, 18.75, TransactionAction.EXPENSE, "Café com amigos");
    Transaction t6 = new Transaction(newAccount, 500.00, TransactionAction.INCOME, "Freelance de design gráfico");
    Transaction t7 = new Transaction(newAccount, 120.30, TransactionAction.EXPENSE, "Conta de energia");
    Transaction t8 = new Transaction(newAccount, 75.00, TransactionAction.EXPENSE, "Reparo no carro");
    Transaction t9 = new Transaction(newAccount, 1000.00, TransactionAction.INCOME, "Bônus de desempenho");
    Transaction t10 = new Transaction(newAccount, 60.00, TransactionAction.EXPENSE, "Compra de livros");

    transactionDao.register(new Transaction[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10});
  }

  public User loginUser(String email, String password) throws SQLException {
    if (email == null || email.trim().isEmpty() ||
        password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Email e senha são obrigatórios");
    }

    User user = userDao.login(email, password);
    if (user == null) {
      throw new UserRegistrationException(UserRegistrationException.Reason.INVALID_CREDENTIALS, "Credenciais inválidas");
    }

    return user;
  }

  private boolean isValidEmail(String email) {
    return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email);
  }
}
