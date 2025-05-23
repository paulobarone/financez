package br.com.fiap.financez.exception;

public class InvalidEmailFormatException extends RuntimeException {
  public InvalidEmailFormatException(String message) {
    super(message);
  }
}
