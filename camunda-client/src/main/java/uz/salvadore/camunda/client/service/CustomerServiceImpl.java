package uz.salvadore.camunda.client.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.salvadore.camunda.client.dto.CustomerInfo;
import uz.salvadore.camunda.client.dto.FilialInfo;
import uz.salvadore.camunda.client.dto.OverdueInfo;
import uz.salvadore.camunda.client.dto.SalaryInfo;
import uz.salvadore.camunda.client.exception.ApplicationFindException;
import uz.salvadore.camunda.client.exception.FilialException;
import uz.salvadore.camunda.client.exception.OverdueException;
import uz.salvadore.camunda.client.exception.SalaryException;
import uz.salvadore.camunda.client.repository.JpaApplicationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {

  JpaApplicationRepository repository;
  ExchangeService service;

  @Override
  @Transactional
  public void checkClient(Long appId) {
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final CustomerInfo info = service.getCustomerInfo(entity.getCustomer().getPinpp());
        entity.getCustomer().setClientUid(info.getClientUid());
        entity.getCustomer().setFirstName(info.getFirstName());
        entity.getCustomer().setLastName(info.getLastName());
        entity.getCustomer().setBirthDate(LocalDate.of(1987, Month.DECEMBER, 7));
        entity.getCustomer().setCardNumber(info.getCardNumber());
      }, ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void updateClient(Long appId) {
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final CustomerInfo info = service.getCustomerInfo(entity.getCustomer().getPinpp());
        entity.getCustomer().setClientId(info.getClientId());
        entity.getCustomer().setClientCode(info.getClientCode());
      }, ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void checkSalary(Long appId) {
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final SalaryInfo salary = service.getSalary(entity.getCustomer().getClientUid());
        if (salary.getSalary().compareTo(BigDecimal.ZERO) < 0) {
          throw new SalaryException();
        }
      }, ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void checkOverdue(Long appId) {
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final OverdueInfo overdue = service.getOverdue(entity.getCustomer().getClientUid());
        final BigDecimal totalAmount = overdue.getItems().stream().map(OverdueInfo.OverdueItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalAmount.compareTo(BigDecimal.ZERO) > 0) {
          throw new OverdueException();
        }
      }, ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void checkFilial(Long appId) {
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final FilialInfo filial = service.getFilial(entity.getCustomer().getClientUid());
        if (!filial.getCodeFilial().equals("00450")) {
          throw new FilialException();
        }
      }, ApplicationFindException::new);
  }
}
