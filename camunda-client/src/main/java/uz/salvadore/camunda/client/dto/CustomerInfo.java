package uz.salvadore.camunda.client.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerInfo {
  String firstName;
  String lastName;
  LocalDate birthDate;
  String pinpp;
  String clientUid;
  String clientId;
  String clientCode;
}
