package uz.salvadore.camunda.client.exception;

public class OverdueException extends DomainException {
  public OverdueException() {
  }

  public OverdueException(String message) {
    super(message);
  }
}
