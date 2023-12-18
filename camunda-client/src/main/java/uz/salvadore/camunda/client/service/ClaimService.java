package uz.salvadore.camunda.client.service;

import uz.salvadore.camunda.client.dto.ApplicationDto;

public interface ClaimService {
  Long startProcess(ApplicationDto application);
}
