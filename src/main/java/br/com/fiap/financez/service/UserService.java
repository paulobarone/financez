package br.com.fiap.financez.service;

import br.com.fiap.financez.dao.UserDao;
import br.com.fiap.financez.exception.*;
import br.com.fiap.financez.model.User;

import java.sql.SQLException;
import java.util.regex.Pattern;

import static br.com.fiap.financez.exception.UserRegistrationException.Reason.EMAIL_INVALID;
import static br.com.fiap.financez.exception.UserRegistrationException.Reason.EMPRY_FIELDS;

public class UserService {
  private final UserDao userDao;

  public UserService() throws SQLException {
    this.userDao = new UserDao();
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
  }

  public void loginUser(String email, String password) throws SQLException {
    if (email == null || email.trim().isEmpty() ||
        password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Email e senha são obrigatórios");
    }

    User user = userDao.login(email, password);
    if (user == null) {
      throw new UserRegistrationException(UserRegistrationException.Reason.INVALID_CREDENTIALS, "Credenciais inválidas");
    }
  }

  private boolean isValidEmail(String email) {
    return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email);
  }
}
