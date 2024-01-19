package uz.salvadore.camunda.client.service;

import uz.salvadore.camunda.client.dto.CustomerInfo;
import uz.salvadore.camunda.client.dto.FilialInfo;
import uz.salvadore.camunda.client.dto.OverdueInfo;
import uz.salvadore.camunda.client.dto.PaymentResponse;
import uz.salvadore.camunda.client.dto.SalaryInfo;

import java.math.BigDecimal;

public interface ExchangeService {

  CustomerInfo getCustomerInfo(String pinpp);
  void updateCustomer(CustomerInfo customerInfo);
  SalaryInfo getSalary(String clientUid);
  OverdueInfo getOverdue(String clientUid);
  FilialInfo getFilial(String clientUid);
  PaymentResponse writeOff(String cardNumber, BigDecimal amount);
  PaymentResponse creditAmountToProvider(String provider, BigDecimal amount);
  PaymentResponse returnFounds(String provider, BigDecimal amount);

}
