package uz.salvadore.camunda.client.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTIONS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionEntity extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(name = "EXTERNAL_ID", unique = true)
  String externalId;
  String merchant;
  BigDecimal amount;
  String cardNumber;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TransactionEntity)) return false;
    TransactionEntity that = (TransactionEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
