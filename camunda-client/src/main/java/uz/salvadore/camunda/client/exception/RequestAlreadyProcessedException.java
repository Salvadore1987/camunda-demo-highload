package uz.salvadore.camunda.client.exception;

public class RequestAlreadyProcessedException extends DomainException {
  public RequestAlreadyProcessedException() {
  }

  public RequestAlreadyProcessedException(String message) {
    super(message);
  }
}
