package br.com.fiap.financez.dao;

import br.com.fiap.financez.factory.ConnectionFactory;
import br.com.fiap.financez.model.User;
import br.com.fiap.financez.model.enums.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
  private final Connection connection;

  public UserDao() throws SQLException {
    this.connection = ConnectionFactory.getConnection();
  }

  public void register(User[] users) throws SQLException {
    connection.setAutoCommit(false);

    for (User user : users) {
      try {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO USERS2 (NAME, EMAIL, PASSWORD, RG, CPF, GENDER) VALUES (?, ?, ?, ?, ?, ?)", new String[]{"ID_USER2"});

        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.setString(4, user.getRg());
        stm.setString(5, user.getCpf());
        stm.setString(6, user.getGender().getDatabaseFormattedGender());

        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
          user.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }

        System.out.println("Usuário registrado com sucesso");
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        System.err.println("Não foi possível registrar o usuário: " + e.getMessage());
      }
    }
  }

  public User getUser(int id) throws SQLException {
    try {
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM USERS2 WHERE ID_USER2 = ?");
      stm.setInt(1, id);

      ResultSet result = stm.executeQuery();
      if (result.next()) {
        int userId = result.getInt("id_user2");
        String name = result.getString("name");
        String email = result.getString("email");
        String password = result.getString("password");
        String rg = result.getString("rg");
        String cpf = result.getString("cpf");
        String genderStr = result.getString("gender");
        Timestamp createdAt = result.getTimestamp("created_at");

        Gender gender = Gender.fromDatabaseFormattedGender(genderStr);
        return new User(userId, name, email, password, rg, cpf, gender, createdAt);
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

    PreparedStatement stm = connection.prepareStatement("SELECT * FROM USERS2");
    ResultSet result = stm.executeQuery();

    while (result.next()) {
      int userId = result.getInt("id_user2");
      String name = result.getString("name");
      String email = result.getString("email");
      String password = result.getString("password");
      String rg = result.getString("rg");
      String cpf = result.getString("cpf");
      String genderStr = result.getString("gender");
      Timestamp createdAt = result.getTimestamp("created_at");

      Gender gender = Gender.fromDatabaseFormattedGender(genderStr);
      users.add(new User(userId, name, email, password, rg, cpf, gender, createdAt));
    }

    return users;
  }

  public void deleteUser(int id) throws SQLException {
    String userName = getUser(id).getName();

    try {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM USERS2 WHERE ID_USER2 = ?");

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
        PreparedStatement stm = connection.prepareStatement("DELETE FROM USERS2");
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