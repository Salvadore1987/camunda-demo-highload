package uz.salvadore.camunda.client.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.salvadore.camunda.client.common.Constants;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaErrorUtil {

  public static Map<String, Object> buildBpmnError(final int code, final String message, final String errorState) {
    return Map.of(Constants.ERROR_CODE, code, Constants.ERROR_MESSAGE, message, Constants.TASK_STATE, errorState);
  }

  public static Map<String, Object> buildBpmnError(final String message, final String errorState) {
    return Map.of(Constants.ERROR_MESSAGE, message, Constants.TASK_STATE, errorState);
  }

}
