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
import uz.salvadore.camunda.client.exception.DomainException;
import uz.salvadore.camunda.client.exception.ProcessVariableNotFound;
import uz.salvadore.camunda.client.service.ApplicationService;
import uz.salvadore.camunda.client.service.CustomerService;
import uz.salvadore.camunda.client.utils.CamundaErrorUtil;
import uz.salvadore.camunda.client.utils.CamundaUtils;

import java.util.Map;

import static uz.salvadore.camunda.client.common.Constants.PROCESS_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
@ExternalTaskSubscription(
  topicName = CamundaTopics.CHECK_SALARY,
  lockDuration = 60000,
  variableNames = {"appId", "state"},
  processDefinitionKey = PROCESS_KEY,
  includeExtensionProperties = true
)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckSalaryHandler implements ExternalTaskHandler {

  ApplicationService applicationService;
  CustomerService customerService;

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    try {
      externalTask.getAllVariables().forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));
      Long appId = (Long) CamundaUtils.getVariableByNameOrElseThrow(externalTask, Constants.APP_ID, ProcessVariableNotFound::new);
      log.info("Application id: {}", appId);
      customerService.checkSalary(appId);
      applicationService.updateState(appId, State.SALARY_CHECKED);
      externalTaskService.complete(externalTask, Map.of(Constants.TASK_STATE, State.SALARY_CHECKED));
    } catch (Exception ex) {
      log.error(ExceptionUtils.getMessage(ex));
      if (ex instanceof DomainException) {
        final Map<String, Object> variables = CamundaErrorUtil.buildBpmnError(
          ex.getMessage(),
          State.CHECK_SALARY.getTitle());
        externalTaskService.handleBpmnError(externalTask, ex.getMessage(), ExceptionUtils.getStackTrace(ex), variables);
      }
      final Integer retries = externalTask.getRetries();
      externalTaskService.handleFailure(externalTask, ex.getMessage(), ExceptionUtils.getStackTrace(ex), retries - 1, 5000);
    }
  }
}
