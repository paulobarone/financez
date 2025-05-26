package br.com.fiap.financez.service;

import br.com.fiap.financez.dao.TransactionDao;
import br.com.fiap.financez.dao.UserDao;
import br.com.fiap.financez.exception.*;
import br.com.fiap.financez.model.Transaction;
import br.com.fiap.financez.model.User;
import br.com.fiap.financez.model.enums.TransactionAction;

import java.sql.SQLException;
import java.util.regex.Pattern;

import static br.com.fiap.financez.exception.UserRegistrationException.Reason.EMAIL_INVALID;
import static br.com.fiap.financez.exception.UserRegistrationException.Reason.EMPRY_FIELDS;

public class UserService {
  private final UserDao userDao;
  private final TransactionDao transactionDao;

  public UserService() throws SQLException {
    this.userDao = new UserDao();
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

    User newUser = new User(name, email, password, 500.00, cpf);
    userDao.register(newUser);

    Transaction t1 = new Transaction(newUser, 750.00, TransactionAction.INCOME);
    Transaction t2 = new Transaction(newUser, 45.90, TransactionAction.EXPENSE);
    Transaction t3 = new Transaction(newUser, 22.50, TransactionAction.EXPENSE);
    Transaction t4 = new Transaction(newUser, 200.00, TransactionAction.INCOME);
    Transaction t5 = new Transaction(newUser, 18.75, TransactionAction.EXPENSE);
    Transaction t6 = new Transaction(newUser, 500.00, TransactionAction.INCOME);
    Transaction t7 = new Transaction(newUser, 120.30, TransactionAction.EXPENSE);
    Transaction t8 = new Transaction(newUser, 75.00, TransactionAction.EXPENSE);
    Transaction t9 = new Transaction(newUser, 1000.00, TransactionAction.INCOME);
    Transaction t10 = new Transaction(newUser, 60.00, TransactionAction.EXPENSE);

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
