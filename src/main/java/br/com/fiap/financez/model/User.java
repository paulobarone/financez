package br.com.fiap.financez.model;

import java.sql.Timestamp;

public class User {
  private int id;
  private final String name;
  private String email;
  private String password;
  private final String cpf;
  private Timestamp createdAt;

  public User(String name, String email, String password, String cpf) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.cpf = cpf;
  }

  public User(int id, String name, String email, String password, String cpf, Timestamp createdAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.cpf = cpf;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCpf() {
    return cpf;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
