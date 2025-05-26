package br.com.fiap.financez.dao;

import br.com.fiap.financez.exception.UserRegistrationException;
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
    String sql = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, BALANCE, CPF) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(sql, new String[]{"ID_USER"})) {
      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getPassword());
      stm.setDouble(4, user.getBalance());
      stm.setString(5, user.getCpf());
      stm.executeUpdate();
      try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          user.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Erro ao encontrar ID");
        }
      }
    } catch (SQLException e) {
      if (e.getErrorCode() == 1) {
        String errorMessage = e.getMessage().toLowerCase();
        System.err.println(errorMessage);
        throw new UserRegistrationException(UserRegistrationException.Reason.FIELD_ALREADY_EXISTS, "E-mail ou CPF já cadastrado");
      } else {
        throw e;
      }
    }
  }

  public User getUser(int id) throws SQLException {
    String sql = "SELECT * FROM USERS WHERE ID_USER = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int userId = result.getInt("id_user");
          String name = result.getString("name");
          String email = result.getString("email");
          String password = result.getString("password");
          double balance = result.getDouble("balance");
          String cpf = result.getString("cpf");
          Timestamp createdAt = result.getTimestamp("created_at");
          return new User(userId, name, email, password, balance, cpf, createdAt);
        }
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível coletar os dados do usuário com ID " + id + ": " + e.getMessage());
    }
    return null;
  }

  public List<User> getAll() throws SQLException {
    List<User> users = new ArrayList<>();
    String sql = "SELECT * FROM USERS";
    try (PreparedStatement stm = connection.prepareStatement(sql);
         ResultSet result = stm.executeQuery()) {
      while (result.next()) {
        int userId = result.getInt("id_user");
        String name = result.getString("name");
        String email = result.getString("email");
        String password = result.getString("password");
        double balance = result.getDouble("balance");
        String cpf = result.getString("cpf");
        Timestamp createdAt = result.getTimestamp("created_at");
        users.add(new User(userId, name, email, password, balance, cpf, createdAt));
      }
    } catch (SQLException e) {
      System.err.println("Não foi possível coletar os dados dos usuários: " + e.getMessage());
      return null;
    }
    return users;
  }

  public void updateBalance(int userId, double newBalance) throws SQLException {
    String sql = "UPDATE users SET balance = ? WHERE id_user = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setDouble(1, newBalance);
      stm.setInt(2, userId);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Não foi possível atualizar o saldo do usuário com ID " + userId + ": " + e.getMessage());
      throw e;
    }
  }

  public void deleteUser(int id) throws SQLException {
    String sql = "DELETE FROM USERS WHERE ID_USER = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setInt(1, id);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Não foi possível deletar o usuário com ID " + id + ": " + e.getMessage());
    }
  }

  public void deleteAll() throws SQLException {
    List<User> users = getAll();
    if (users != null && !users.isEmpty()) {
      String sql = "DELETE FROM USERS";
      try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.executeUpdate();
      } catch (SQLException e) {
        System.err.println("Não foi possível deletar os usuários: " + e.getMessage());
      }
    } else {
      System.err.println("Não há usuários para serem deletados");
    }
  }

  public User login(String email, String password) throws SQLException {
    String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
      stm.setString(1, email);
      stm.setString(2, password);
      try (ResultSet result = stm.executeQuery()) {
        if (result.next()) {
          int userId = result.getInt("id_user");
          return getUser(userId);
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao tentar fazer login: " + e.getMessage());
      throw new UserRegistrationException(UserRegistrationException.Reason.UNKNOWN, "Erro ao tentar fazer login");
    }
    return null;
  }
}