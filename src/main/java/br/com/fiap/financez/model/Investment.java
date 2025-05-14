package br.com.fiap.financez.model;

import java.sql.Timestamp;

public class Investment {
  private int id;
  private Account account;
  private InvestmentOption investmentOption;
  private double amount;
  private String description;
  private Timestamp createdAt;

  public Investment(Account account, InvestmentOption investmentOption, double amount) {
    this.account = account;
    this.investmentOption = investmentOption;
    this.amount = amount;
  }

  public Investment(Account account, InvestmentOption investmentOption, double amount, String description) {
    this.account = account;
    this.investmentOption = investmentOption;
    this.amount = amount;
    this.description = description;
  }

  public Investment(int id, Account account, InvestmentOption investmentOption, double amount, String description, Timestamp createdAt) {
    this.id = id;
    this.account = account;
    this.investmentOption = investmentOption;
    this.amount = amount;
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

  public InvestmentOption getInvestmentOption() {
    return investmentOption;
  }

  public void setInvestmentOption(InvestmentOption investmentOption) {
    this.investmentOption = investmentOption;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
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
