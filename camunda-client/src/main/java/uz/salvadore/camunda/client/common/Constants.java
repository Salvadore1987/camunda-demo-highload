package uz.salvadore.camunda.client.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final String TASK_STATE = "state";
  public static final String APP_ID = "appId";
  public static final String ERROR_CODE = "errorCode";
  public static final String ERROR_MESSAGE = "errorMessage";
  public static final String PROCESS_KEY = "credit-demo";
  public static final Long LOCK_DURATION = 60000L;
}
