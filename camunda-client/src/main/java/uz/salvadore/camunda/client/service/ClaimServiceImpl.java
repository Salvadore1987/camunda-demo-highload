package uz.salvadore.camunda.client.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import uz.salvadore.camunda.client.dto.ApplicationDto;
import uz.salvadore.camunda.client.dto.StartProcess;
import uz.salvadore.camunda.client.event.StartProcessEvent;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClaimServiceImpl implements ClaimService {

  ApplicationService service;
  ApplicationEventPublisher publisher;

  @Override
  public Long startProcess(ApplicationDto application) {
    final Long appId = service.save(application);
    final String claimNumber = String.format("2023.%d", appId);
    StartProcess startProcess = StartProcess.builder()
      .appId(appId)
      .claimCode(claimNumber)
      .params(buildParamsMap(appId))
      .build();
    log.info("Begin publish Start Process Event appId: {}, claimCode:{}", appId, claimNumber);
    publisher.publishEvent(new StartProcessEvent(this, startProcess));
    return appId;
  }

  private HashMap<String, Object> buildParamsMap(final Long appId) {
    final HashMap<String, Object> params = new HashMap<>();
    params.put("appId", appId);
    return params;
  }

}
