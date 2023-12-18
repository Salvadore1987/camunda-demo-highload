package uz.salvadore.camunda.client.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.salvadore.camunda.client.dto.CustomerDto;
import uz.salvadore.camunda.client.entity.CustomerEntity;

import javax.annotation.PostConstruct;

import static org.modelmapper.Conditions.isNotNull;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerDtoToCustomerEntityMapper {

  ModelMapper mapper;

  @PostConstruct
  public void init() {
    mapper.createTypeMap(CustomerDto.class, CustomerEntity.class)
      .addMappings(m -> {
        m.when(isNotNull()).map(CustomerDto::getPinpp, CustomerEntity::setPinpp);
      });
  }

}
