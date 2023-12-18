package uz.salvadore.camunda.client.exception;

public class ApplicationFindException extends DomainException {
  public ApplicationFindException() {
    super("Can't find application");
  }

  public ApplicationFindException(String message) {
    super(message);
  }
}
