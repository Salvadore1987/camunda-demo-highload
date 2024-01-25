package uz.salvadore.camunda.client.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import uz.salvadore.camunda.client.dto.Transaction;
import uz.salvadore.camunda.client.entity.TransactionEntity;
import uz.salvadore.camunda.client.exception.RequestAlreadyProcessedException;
import uz.salvadore.camunda.client.repository.JpaTransactionRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionServiceImpl implements TransactionService {

  JpaTransactionRepository repository;
  ModelMapper mapper;

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public Long save(Transaction transaction) {
    if (repository.findByExternalId(transaction.getExternalId()).isPresent()) {
      throw new RequestAlreadyProcessedException("Request already processed");
    }
    return Optional.ofNullable(transaction)
      .map(trx -> mapper.map(trx, TransactionEntity.class))
      .map(repository::save)
      .map(TransactionEntity::getId)
      .orElseThrow();
  }

  @Override
  public Transaction getTransaction(Long id) {
    return Optional.ofNullable(id)
      .flatMap(repository::findById)
      .map(entity -> mapper.map(entity, Transaction.class))
      .orElseThrow();
  }
}
