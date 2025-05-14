package br.com.fiap.financez.model;

import br.com.fiap.financez.model.enums.RiskLevel;

public class InvestmentOption {
  private int id;
  private String name;
  private RiskLevel riskLevel;
  private double rate;

  public InvestmentOption(String name, RiskLevel riskLevel, double rate) {
    this.name = name;
    this.riskLevel = riskLevel;
    this.rate = rate;
  }

  public InvestmentOption(int id, String name, RiskLevel riskLevel, double rate) {
    this.id = id;
    this.name = name;
    this.riskLevel = riskLevel;
    this.rate = rate;
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

  public void setName(String name) {
    this.name = name;
  }

  public RiskLevel getRiskLevel() {
    return riskLevel;
  }

  public void setRiskLevel(RiskLevel riskLevel) {
    this.riskLevel = riskLevel;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }
}
