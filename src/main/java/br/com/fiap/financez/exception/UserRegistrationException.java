package br.com.fiap.financez.exception;

public class UserRegistrationException extends RuntimeException {

  public enum Reason {
    EMPRY_FIELDS,
    EMAIL_INVALID,
    FIELD_ALREADY_EXISTS,
    INVALID_CREDENTIALS,
    UNKNOWN
  }

  private final Reason reason;

  public UserRegistrationException(Reason reason, String message) {
    super(message);
    this.reason = reason;
  }

  public UserRegistrationException(Reason reason, String message, Throwable cause) {
    super(message, cause);
    this.reason = reason;
  }

  public Reason getReason() {
    return reason;
  }
}