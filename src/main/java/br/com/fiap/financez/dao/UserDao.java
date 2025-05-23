package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
  private final Connection connection;

  public UserDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(User user) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("INSERT INTO USERS (NAME, EMAIL, PASSWORD, CPF) VALUES (?, ?, ?, ?)", new String[]{"ID_USER"});

      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getPassword());
      stm.setString(4, user.getCpf());

      stm.executeUpdate();

      ResultSet generatedKeys = stm.getGeneratedKeys();
      if (generatedKeys.next()) {
        user.setId(generatedKeys.getInt(1));
      } else {
        throw new SQLException("Erro ao encontrar ID");
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível registrar o usuário " + user.getName() + ": " + e.getMessage());
      connection.rollback();
      throw e;
    }
  }

  public User getUser(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM USERS WHERE ID_USER = ?");
      stm.setInt(1, id);

      ResultSet result = stm.executeQuery();
      if (result.next()) {
        int userId = result.getInt("id_user");
        String name = result.getString("name");
        String email = result.getString("email");
        String password = result.getString("password");
        String cpf = result.getString("cpf");
        Timestamp createdAt = result.getTimestamp("created_at");

        return new User(userId, name, email, password, cpf, createdAt);
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível coletar os dados do usuário com ID " + id + ": " + e.getMessage());
      return null;
    }
  }

  public List<User> getAll() throws SQLException {
    List<User> users = new ArrayList<>();

    PreparedStatement stm = connection.prepareStatement("SELECT * FROM USERS");
    ResultSet result = stm.executeQuery();

    while (result.next()) {
      int userId = result.getInt("id_user");
      String name = result.getString("name");
      String email = result.getString("email");
      String password = result.getString("password");
      String cpf = result.getString("cpf");
      Timestamp createdAt = result.getTimestamp("created_at");

      users.add(new User(userId, name, email, password, cpf, createdAt));
    }

    return users;
  }

  public void deleteUser(int id) throws SQLException {
    String userName = getUser(id).getName();

    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM USERS WHERE ID_USER = ?");

      stm.setInt(1, id);

      stm.executeUpdate();
      System.out.println("Usuário deletado com sucesso");
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar o usuário com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<User> users = getAll();

    if (!users.isEmpty()) {
      try {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM USERS");
        stm.executeUpdate();
        System.out.println("Usuários deletados com sucesso");
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar os usuários: " + e.getMessage());
      }
    } else {
      System.err.println("Não há usuários para serem deletados");
    }
  }
}