package uz.salvadore.camunda.client.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractEntity implements Serializable {

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  LocalDateTime createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false)
  LocalDateTime updatedAt;
  @Column(name = "is_deleted", columnDefinition = "boolean default false")
  boolean deleted;
  @Version
  @Column(columnDefinition = "bigint default 0")
  int version;

}
