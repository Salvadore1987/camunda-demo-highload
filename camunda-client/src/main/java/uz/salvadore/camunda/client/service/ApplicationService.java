package uz.salvadore.camunda.client.service;

import uz.salvadore.camunda.client.common.State;
import uz.salvadore.camunda.client.dto.ApplicationDto;

public interface ApplicationService {
  void updateState(Long appId, State state);
  Long save(ApplicationDto application);
  void setClaimNumber(Long appId, String claimNumber);
  void setProcessId(Long appId, String processId);
}
