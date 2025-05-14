package br.com.fiap.financez.model.enums;

public enum RiskLevel {
  LOW("Baixo"),
  MEDIUM("Médio"),
  HIGH("Alto");

  private String risk;

  RiskLevel(String risk) {
    this.risk = risk;
  }

  public String getFormattedRisk() {
    return this.risk;
  }

  public String getDatabaseFormattedRisk() {
    return this.name().charAt(0) + this.name().substring(1).toLowerCase();
  }

  public static RiskLevel fromDatabaseFormattedRisk(String dbValue) {
    return switch (dbValue) {
      case "Low" -> LOW;
      case "Medium" -> MEDIUM;
      case "High" -> HIGH;
      default -> throw new IllegalArgumentException("Nível de risco não reconhecido: " + dbValue);
    };
  }

  public void setRisk(String risk) {
    this.risk = risk;
  }
}