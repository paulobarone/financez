package br.com.fiap.financez.model.enums;

public enum TransactionAction {
  INCOME("Rendimento"),
  EXPENSE("Despesa");

  private String transactionAction;

  TransactionAction(String transactionAction) {
    this.transactionAction = transactionAction;
  }

  public String getFormattedAction() {
    return this.transactionAction;
  }

  public String getDatabaseFormattedAction() {
    return this.name().charAt(0) + this.name().substring(1).toLowerCase();
  }

  public static TransactionAction fromDatabaseFormattedAction(String dbValue) {
    return switch (dbValue) {
      case "Income" -> INCOME;
      case "Expense" -> EXPENSE;
      default -> throw new IllegalArgumentException("Tipo de transação não reconhecido: " + dbValue);
    };
  }

  public void setTransactionAction(String transactionAction) {
    this.transactionAction = transactionAction;
  }
}