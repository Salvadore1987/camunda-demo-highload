package uz.salvadore.camunda.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salvadore.camunda.client.entity.TransactionEntity;

import java.util.Optional;

public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {
  Optional<TransactionEntity> findByExternalId(String externalId);
}
