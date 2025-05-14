package br.com.fiap.financez.exception;

public class InvalidTransactionException extends RuntimeException {
  public InvalidTransactionException(String message) {
    super(message);
  }
}
