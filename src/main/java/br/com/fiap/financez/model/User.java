package br.com.fiap.financez.model;

import br.com.fiap.financez.model.enums.Gender;

import java.sql.Timestamp;

public class User {
  private int id;
  private final String name;
  private String email;
  private String password;
  private final String rg;
  private final String cpf;
  private Gender gender;
  private Timestamp createdAt;

  public User(String name, String email, String password, String rg, String cpf, Gender gender) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.rg = rg;
    this.cpf = cpf;
    this.gender = gender;
  }

  public User(int id, String name, String email, String password, String rg, String cpf, Gender gender, Timestamp createdAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.rg = rg;
    this.cpf = cpf;
    this.gender = gender;
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

  public String getRg() {
    return rg;
  }

  public String getCpf() {
    return cpf;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
