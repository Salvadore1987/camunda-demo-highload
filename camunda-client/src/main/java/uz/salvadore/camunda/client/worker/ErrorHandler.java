package uz.salvadore.camunda.client.worker;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;
import uz.salvadore.camunda.client.common.CamundaTopics;
import uz.salvadore.camunda.client.common.Constants;
import uz.salvadore.camunda.client.common.State;

import static uz.salvadore.camunda.client.common.Constants.PROCESS_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
@ExternalTaskSubscription(
  topicName = CamundaTopics.ERROR_HANDLER,
  lockDuration = 60000,
  variableNames = {"appId", "state"},
  processDefinitionKey = PROCESS_KEY,
  includeExtensionProperties = true
)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorHandler implements ExternalTaskHandler {
  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    try {
      final Long appId = externalTask.getVariable(Constants.APP_ID);
      final State state = externalTask.getVariable(Constants.TASK_STATE);
      log.error("Error appId: {}, state: {}", appId, state);
      externalTaskService.complete(externalTask);
    } catch (Exception ex) {
      log.error("ErrorHandler: {}", ExceptionUtils.getMessage(ex));
      final Integer retries = externalTask.getRetries();
      externalTaskService.handleFailure(externalTask, ex.getMessage(),
        ExceptionUtils.getStackTrace(ex), retries - 1, 5000);
    }
  }
}
