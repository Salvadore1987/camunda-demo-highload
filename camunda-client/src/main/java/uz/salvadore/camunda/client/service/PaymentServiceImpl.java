package uz.salvadore.camunda.client.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.salvadore.camunda.client.dto.PaymentResponse;
import uz.salvadore.camunda.client.exception.ApplicationFindException;
import uz.salvadore.camunda.client.exception.WriteOffException;
import uz.salvadore.camunda.client.repository.JpaApplicationRepository;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentServiceImpl implements PaymentService {

  JpaApplicationRepository repository;
  ExchangeService service;

  @Override
  @Transactional
  public void writeOff(Long appId, BigDecimal amount) {
    log.info("WRITE OFF found for appId: {}", appId);
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final PaymentResponse response = service.writeOff(entity.getCustomer().getCardNumber(), amount);
        if (response.getCode() != 0) {
          throw new WriteOffException("Payment error");
        }
      }, ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void creditAmountToProvider(Long appId, String provider, BigDecimal amount) {
    log.info("CREDIT AMOUNT for appId: {}", appId);
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final PaymentResponse response = service.creditAmountToProvider(provider, amount);
        if (response.getCode() != 0) {
          throw new WriteOffException("Payment error");
        }
      }, ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void returnFounds(Long appId, String provider, BigDecimal amount) {
    log.info("RETURN FOUNDS for appId: {}", appId);
    repository.findById(appId)
      .ifPresentOrElse(entity -> {
        final PaymentResponse response = service.returnFounds(provider, amount);
        if (response.getCode() != 0) {
          throw new WriteOffException("Payment error");
        }
      }, ApplicationFindException::new);
  }
}
