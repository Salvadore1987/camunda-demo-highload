package uz.salvadore.camunda.client.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import uz.salvadore.camunda.client.common.State;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationEntity extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String claimNumber;
  @Enumerated(value = EnumType.STRING)
  State state;
  String processId;
  @ToString.Exclude
  @JoinColumn(name = "CUSTOMER_ID")
  @OneToOne(fetch = FetchType.LAZY, targetEntity = CustomerEntity.class, cascade = CascadeType.ALL)
  CustomerEntity customer;

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ApplicationEntity)) return false;
    if (!super.equals(o)) return false;
    ApplicationEntity that = (ApplicationEntity) o;
    return id.equals(that.id);
  }

  public int hashCode() {
    return Objects.hash(super.hashCode(), id);
  }
}
