package uz.salvadore.camunda.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salvadore.camunda.client.entity.ApplicationEntity;

@Repository
public interface JpaApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
}
