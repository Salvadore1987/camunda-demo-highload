package uz.salvadore.camunda.client.exception;

public class PaymentException extends DomainException {
  public PaymentException() {
  }

  public PaymentException(String message) {
    super(message);
  }
}
