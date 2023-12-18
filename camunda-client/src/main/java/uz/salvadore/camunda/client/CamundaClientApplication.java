package uz.salvadore.camunda.client;

import org.camunda.bpm.client.spring.annotation.EnableExternalTaskClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableExternalTaskClient
public class CamundaClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(CamundaClientApplication.class, args);
  }
}