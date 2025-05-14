package br.com.fiap.financez.model;

import java.sql.Timestamp;

public class Account {
  private int id;
  private User accountHolder;
  private String number;
  private String agency;
  private double balance;
  private Timestamp createdAt;

  public Account(User accountHolder, String number, String agency, double balance) {
    this.accountHolder = accountHolder;
    this.number = number;
    this.agency = agency;
    this.balance = balance;
  }

  public Account(int id, User accountHolder, String number, String agency, double balance, Timestamp createdAt) {
    this.id = id;
    this.accountHolder = accountHolder;
    this.number = number;
    this.agency = agency;
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

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getAgency() {
    return agency;
  }

  public void setAgency(String agency) {
    this.agency = agency;
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
