package uz.salvadore.camunda.client.common;

import java.util.Arrays;

public enum State {
  NEW("Новая"),
  CHECK_CLIENT("Проверка клиента"),
  CLIENT_CHECKED("Клиент успешно проверен"),
  UPDATE_CLIENT("Обновление клиента"),
  CLIENT_UPDATED("Клиент успешно обновлен"),
  CHECK_SALARY("Проверка на зарплату"),
  SALARY_CHECKED("Проверка на ЗП проведена"),
  CHECK_OVERDUE("Проверка на просрочки"),
  OVERDUE_CHECKED("Проверка на просрочки прошла успешно"),
  CHECK_FILIAL("Проверка филиала"),
  FILIAL_CHECKED("Проверка филиала прошла успешно"),
  EXPIRED("Истекла"),
  FINISHED("Завершена"),
  ERROR("Ошибка");

  final String title;

  State(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public static State of(String state) {
    return Arrays.stream(State.values())
      .filter(i -> i.title.equals(state))
      .findFirst()
      .orElseThrow();
  }
}
