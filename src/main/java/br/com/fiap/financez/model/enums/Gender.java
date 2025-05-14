package br.com.fiap.financez.model.enums;

public enum Gender {
  MALE("Masculino"),
  FEMALE("Feminino");

  private String gender;

  Gender(String gender) {
    this.gender = gender;
  }

  public String getFormattedGender() {
    return this.gender;
  }

  public String getDatabaseFormattedGender() {
    return switch (this) {
      case MALE -> "M";
      case FEMALE -> "F";
    };
  }

  public static Gender fromDatabaseFormattedGender(String dbValue) {
    return switch (dbValue) {
      case "M" -> MALE;
      case "F" -> FEMALE;
      default -> throw new IllegalArgumentException("Gênero não reconhecido: " + dbValue);
    };
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}