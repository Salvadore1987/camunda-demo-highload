package uz.salvadore.camunda.client.config;

import org.camunda.bpm.client.backoff.BackoffStrategy;
import org.camunda.bpm.client.task.ExternalTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BackofStrategyConfiguration {

  @Value("${camunda.bpm.client.backoff-strategy-time}")
  private Long backoffStrategyTime;

  @Bean
  public BackoffStrategy backoffStrategy() {
    return new BackoffStrategy() {
      @Override
      public void reconfigure(final List<ExternalTask> externalTasks) {

      }

      @Override
      public long calculateBackoffTime() {
        return backoffStrategyTime;
      }
    };
  }

}
