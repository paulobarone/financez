package br.com.fiap.financez.model;

import br.com.fiap.financez.model.enums.TransactionAction;

import java.sql.Timestamp;

public class Transaction {
  private int id;
  private User user;
  private double amount;
  private TransactionAction action;
  private Timestamp createdAt;

  public Transaction(User user, double amount, TransactionAction action) {
    this.user = user;
    this.amount = amount;
    this.action = action;
  }

  public Transaction(int id, User user, double amount, TransactionAction action, Timestamp createdAt) {
    this.id = id;
    this.user = user;
    this.amount = amount;
    this.action = action;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public TransactionAction getAction() {
    return action;
  }

  public void setAction(TransactionAction action) {
    this.action = action;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
