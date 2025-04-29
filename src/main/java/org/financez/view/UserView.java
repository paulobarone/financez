package org.financez.view;

import org.financez.dao.UserDao;
import org.financez.model.User;

import java.sql.SQLException;
import java.util.List;

import static org.financez.util.ColorsANSI.ANSI_PURPLE;
import static org.financez.util.ColorsANSI.ANSI_RESET;

public class UserView {
  public static void showAllUsers() {
    try {
      UserDao userDao = new UserDao();
      List<User> users = userDao.getAll();

      if (users.isEmpty()) {
        System.err.println("Nenhum usuário encontrado");
      } else {
        System.out.println("-> Lista de usuários:");
        for (User user : users) {
          System.out.println(ANSI_PURPLE + "Usuário" + ANSI_RESET + " ID: " + user.getId() + " | Nome: " + user.getName() + " | E-mail: " + user.getEmail() + " | Senha: " + user.getPassword() + " | RG: " + user.getRg() + " | CPF: " + user.getCpf() + " | Gênero: " + user.getGender().getFormattedGender());
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar usuários: " + e.getMessage());
    }
  }

  public static void showUser(int id) {
    try {
      UserDao userDao = new UserDao();
      User user = userDao.getUser(id);

      if (user != null) {
        System.out.println(ANSI_PURPLE + "Usuário" + ANSI_RESET + " ID: " + user.getId() + " | Nome: " + user.getName() + " | E-mail: " + user.getEmail() + " | Senha: " + user.getPassword() + " | RG: " + user.getRg() + " | CPF: " + user.getCpf() + " | Gênero: " + user.getGender().getFormattedGender());
      } else {
        System.err.println("Usuário com ID " + id + " não encontrado");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar usuário com ID " + id + ": " + e.getMessage());
    }
  }
}
