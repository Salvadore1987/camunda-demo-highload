package uz.salvadore.camunda.client.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String firstName;
  String lastName;
  String pinpp;
  LocalDate birthDate;
  String clientUid;
  String clientId;
  String clientCode;

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CustomerEntity)) return false;
    if (!super.equals(o)) return false;
    CustomerEntity that = (CustomerEntity) o;
    return id.equals(that.id);
  }

  public int hashCode() {
    return Objects.hash(super.hashCode(), id);
  }
}
