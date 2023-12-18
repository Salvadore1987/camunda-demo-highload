package uz.salvadore.camunda.client.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import uz.salvadore.camunda.client.common.Constants;
import uz.salvadore.camunda.client.common.State;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaUtils {

  public static Integer getRetries(final ExternalTask task) {
    Integer retries = task.getRetries();
    if (retries == null) {
      retries = 5;
    } else {
      retries = retries - 1;
    }
    return retries;
  }

  public static String getCurrentTaskState(final ExternalTask task) {
    return Optional.ofNullable(task)
      .map(ExternalTask::getAllVariables)
      .flatMap(v -> v.entrySet().stream()
        .filter(i -> i.getKey().equals(Constants.TASK_STATE))
        .findFirst()
        .map(Map.Entry::getValue))
      .map(State.class::cast)
      .map(Enum::name)
      .orElseThrow();
  }

  public static Map<String, Object> getAllVariables(final ExternalTask task) {
    return Optional.ofNullable(task).map(ExternalTask::getAllVariables).orElse(Collections.emptyMap());
  }

  public static Object getVariableByName(final ExternalTask task, final String key) {
    return getAllVariables(task).get(key);
  }

  public static <T extends RuntimeException> Object getVariableByNameOrElseThrow(final ExternalTask task, final String key, final Supplier<T> runtimeException) {
    Object result;
    try {
      result = getAllVariables(task).get(key);
    } catch (final Exception ex) {
      result = null;
    }
    if (result == null) {
      throw runtimeException.get();
    }
    return result;
  }

  public static Object getVariableByNameOrNull(final ExternalTask task, final String key) {
    try {
      return getAllVariables(task).get(key);
    } catch (final NullPointerException ex) {
      return null;
    }
  }

  public static int getRetryCount(final ExternalTask task, final int defaultRetryCount, final String retryVarName) {
    return Optional.ofNullable(CamundaUtils.getVariableByNameOrNull(task, retryVarName))
      .map(Integer.class::cast)
      .orElse(defaultRetryCount);
  }

  public static <T> T parseJson(final String json, final ObjectMapper mapper, final Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (final Exception ex) {
      log.error(ex.getMessage());
      throw new IllegalArgumentException();
    }
  }

}
