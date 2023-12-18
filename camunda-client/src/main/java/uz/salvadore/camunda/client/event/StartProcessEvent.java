package uz.salvadore.camunda.client.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;
import uz.salvadore.camunda.client.dto.StartProcess;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StartProcessEvent extends ApplicationEvent {
  StartProcess startProcess;

  public StartProcessEvent(final Object source, final StartProcess startProcess) {
    super(source);
    this.startProcess = startProcess;
  }
}
