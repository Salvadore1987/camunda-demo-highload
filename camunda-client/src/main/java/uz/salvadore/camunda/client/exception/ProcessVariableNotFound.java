package uz.salvadore.camunda.client.exception;

public class ProcessVariableNotFound extends DomainException {
  public ProcessVariableNotFound() {
  }

  public ProcessVariableNotFound(String message) {
    super(message);
  }
}
