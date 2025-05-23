package br.com.fiap.financez.service;

import br.com.fiap.financez.dao.UserDao;
import br.com.fiap.financez.exception.*;
import br.com.fiap.financez.model.User;

import java.sql.SQLException;
import java.util.regex.Pattern;

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
      throw new EmptyFieldsException("Todos os campos obrigatórios devem ser preenchidos");
    }

    if (!isValidEmail(email)) {
      throw new InvalidEmailFormatException("O formato do e-mail é inválido");
    }

    User newUser = new User(name, email, password, cpf);

    try {
      userDao.register(newUser);
    } catch (UserRegistrationException e) {
      throw e;
    } catch (SQLException e) {
      throw new SQLException("Erro inesperado no banco de dados ao registrar usuário: " + e.getMessage());
    }
  }

  private boolean isValidEmail(String email) {
    return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email);
  }
}
