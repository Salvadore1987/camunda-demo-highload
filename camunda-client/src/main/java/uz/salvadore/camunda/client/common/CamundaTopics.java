package uz.salvadore.camunda.client.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaTopics {

  public static final String CHECK_CLIENT = "check-client";
  public static final String UPDATE_CLIENT = "update-client";
  public static final String CHECK_SALARY = "check-salary";
  public static final String CHECK_OVERDUE = "check-overdue";
  public static final String CHECK_FILIAL = "check-filial";

}
