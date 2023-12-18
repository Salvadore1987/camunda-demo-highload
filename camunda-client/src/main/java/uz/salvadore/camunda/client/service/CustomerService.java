package uz.salvadore.camunda.client.service;

public interface CustomerService {
  void checkClient(Long appId);
  void updateClient(Long appId);
  void checkSalary(Long appId);
  void checkOverdue(Long appId);
  void checkFilial(Long appId);
}
