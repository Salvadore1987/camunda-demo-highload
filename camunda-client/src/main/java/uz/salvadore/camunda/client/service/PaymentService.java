package uz.salvadore.camunda.client.service;

import java.math.BigDecimal;

public interface PaymentService {
  void writeOff(Long appId, BigDecimal amount);
  void creditAmountToProvider(Long appId, String provider, BigDecimal amount);
  void returnFounds(Long appId, String provider, BigDecimal amount);
}
