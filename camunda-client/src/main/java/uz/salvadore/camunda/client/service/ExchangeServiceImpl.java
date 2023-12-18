package uz.salvadore.camunda.client.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salvadore.camunda.client.dto.CustomerInfo;
import uz.salvadore.camunda.client.dto.FilialInfo;
import uz.salvadore.camunda.client.dto.OverdueInfo;
import uz.salvadore.camunda.client.dto.SalaryInfo;
import uz.salvadore.camunda.client.exception.ExchangeException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExchangeServiceImpl implements ExchangeService {

  @Override
  public CustomerInfo getCustomerInfo(String pinpp) {
    try {
      Thread.sleep(30000L);
      return CustomerInfo.builder()
        .pinpp("30712870220066")
        .birthDate(LocalDate.of(1987, Month.DECEMBER, 7))
        .clientCode("987654321")
        .clientId("12345")
        .clientUid("123456789")
        .firstName("Eldar")
        .lastName("Sagitov")
        .build();
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
      throw new ExchangeException(ex.getMessage());
    }
  }

  @Override
  public void updateCustomer(CustomerInfo customerInfo) {
    try {
      Thread.sleep(30000L);
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
      throw new ExchangeException(ex.getMessage());
    }
  }

  @Override
  public SalaryInfo getSalary(String clientUid) {
    try {
      Thread.sleep(30000L);
      return SalaryInfo.builder()
        .salary(BigDecimal.valueOf(65000000))
        .build();
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
      throw new ExchangeException(ex.getMessage());
    }
  }

  @Override
  public OverdueInfo getOverdue(String clientUid) {
    try {
      Thread.sleep(30000L);
      return OverdueInfo.builder()
        .items(Collections.emptyList())
        .build();
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
      throw new ExchangeException(ex.getMessage());
    }
  }

  @Override
  public FilialInfo getFilial(String clientUid) {
    try {
      Thread.sleep(30000L);
      return FilialInfo.builder()
        .codeFilial("00450")
        .nameFilial("NBU Main Branch")
        .build();
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
      throw new ExchangeException(ex.getMessage());
    }
  }
}
