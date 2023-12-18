package uz.salvadore.camunda.client.service;

import uz.salvadore.camunda.client.dto.CustomerInfo;
import uz.salvadore.camunda.client.dto.FilialInfo;
import uz.salvadore.camunda.client.dto.OverdueInfo;
import uz.salvadore.camunda.client.dto.SalaryInfo;

public interface ExchangeService {

  CustomerInfo getCustomerInfo(String pinpp);
  void updateCustomer(CustomerInfo customerInfo);
  SalaryInfo getSalary(String clientUid);
  OverdueInfo getOverdue(String clientUid);
  FilialInfo getFilial(String clientUid);

}
