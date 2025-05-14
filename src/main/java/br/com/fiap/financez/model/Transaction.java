package br.com.fiap.financez.model;

import br.com.fiap.financez.model.enums.TransactionAction;

import java.sql.Timestamp;

public class Transaction {
  private int id;
  private Account account;
  private double amount;
  private TransactionAction action;
  private String description;
  private Timestamp createdAt;

  public Transaction(Account account, double amount, TransactionAction action) {
    this.account = account;
    this.amount = amount;
    this.action = action;
  }

  public Transaction(Account account, double amount, TransactionAction action, String description) {
    this.account = account;
    this.amount = amount;
    this.action = action;
    this.description = description;
  }

  public Transaction(int id, Account account, double amount, TransactionAction action, String description, Timestamp createdAt) {
    this.id = id;
    this.account = account;
    this.amount = amount;
    this.action = action;
    this.description = description;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
