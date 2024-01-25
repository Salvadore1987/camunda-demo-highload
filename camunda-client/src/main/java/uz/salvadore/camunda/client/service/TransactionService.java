package uz.salvadore.camunda.client.service;

import uz.salvadore.camunda.client.dto.Transaction;

public interface TransactionService {
  Long save(Transaction transaction);
  Transaction getTransaction(Long id);
}
