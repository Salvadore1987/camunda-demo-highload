package uz.salvadore.camunda.client.listener;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.keysoft.camunda.spring.boot.starter.dto.process.StartProcessResult;
import uz.keysoft.camunda.spring.boot.starter.service.ProcessService;
import uz.salvadore.camunda.client.common.Constants;
import uz.salvadore.camunda.client.dto.StartProcess;
import uz.salvadore.camunda.client.event.StartProcessEvent;
import uz.salvadore.camunda.client.service.ApplicationService;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StartProcessListener {

  ProcessService processService;
  ApplicationService service;

  @Async
  @Transactional
  @EventListener(value = StartProcessEvent.class)
  public void onApplicationEvent(final @NonNull StartProcessEvent event) {
    log.info("Begin event listener: {}", event);
    final StartProcess startProcess = event.getStartProcess();
    final StartProcessResult result = processService.startProcessByKey(
      Constants.PROCESS_KEY, startProcess.getClaimCode(), startProcess.getParams());
    service.setClaimNumber(startProcess.getAppId(), startProcess.getClaimCode());
    service.setProcessId(startProcess.getAppId(), result.getId());
    log.info("End event listener: {}", event);
  }
}
