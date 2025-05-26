package br.com.fiap.financez.model;

import java.sql.Timestamp;

public class Investment {
  private int id;
  private int accountId;
  private int investmentOptionId;
  private double amount;
  private Timestamp createdAt;

  public Investment(int accountId, int investmentOptionId, double amount) {
    this.accountId = accountId;
    this.investmentOptionId = investmentOptionId;
    this.amount = amount;
  }

  public Investment(int id, int accountId, int investmentOptionId, double amount, Timestamp createdAt) {
    this.id = id;
    this.accountId = accountId;
    this.investmentOptionId = investmentOptionId;
    this.amount = amount;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public int getInvestmentOptionId() {
    return investmentOptionId;
  }

  public void setInvestmentOptionId(int investmentOptionId) {
    this.investmentOptionId = investmentOptionId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
