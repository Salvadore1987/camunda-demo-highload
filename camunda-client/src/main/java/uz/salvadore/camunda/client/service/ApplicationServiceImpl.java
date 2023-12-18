package uz.salvadore.camunda.client.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.salvadore.camunda.client.common.State;
import uz.salvadore.camunda.client.dto.ApplicationDto;
import uz.salvadore.camunda.client.entity.ApplicationEntity;
import uz.salvadore.camunda.client.exception.ApplicationFindException;
import uz.salvadore.camunda.client.repository.JpaApplicationRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationServiceImpl implements ApplicationService {

  JpaApplicationRepository repository;
  ModelMapper mapper;

  @Override
  @Transactional
  public void updateState(Long appId, State state) {
    repository.findById(appId)
      .ifPresentOrElse(entity -> entity.setState(state), ApplicationFindException::new);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Long save(ApplicationDto application) {
    return Optional.ofNullable(application)
      .map(app -> mapper.map(app, ApplicationEntity.class))
      .map(repository::save)
      .map(ApplicationEntity::getId)
      .orElseThrow();
  }

  @Override
  @Transactional
  public void setClaimNumber(Long appId, String claimNumber) {
    Optional.ofNullable(appId)
      .flatMap(repository::findById)
      .ifPresentOrElse(entity -> entity.setClaimNumber(claimNumber), ApplicationFindException::new);
  }

  @Override
  @Transactional
  public void setProcessId(Long appId, String processId) {
    Optional.ofNullable(appId)
      .flatMap(repository::findById)
      .ifPresentOrElse(entity -> entity.setProcessId(processId), ApplicationFindException::new);
  }
}
