package br.com.fiap.financez.model;

import java.sql.Timestamp;

public class Account {
  private int id;
  private User accountHolder;
  private double balance;
  private Timestamp createdAt;

  public Account(User accountHolder, double balance) {
    this.accountHolder = accountHolder;
    this.balance = balance;
  }

  public Account(int id, User accountHolder, double balance, Timestamp createdAt) {
    this.id = id;
    this.accountHolder = accountHolder;
    this.balance = balance;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(User accountHolder) {
    this.accountHolder = accountHolder;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
