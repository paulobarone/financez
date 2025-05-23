package br.com.fiap.financez.exception;

public class EmptyFieldsException extends RuntimeException {
  public EmptyFieldsException(String message) {
    super(message);
  }
}
