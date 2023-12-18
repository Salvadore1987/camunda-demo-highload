package uz.salvadore.camunda.client.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.salvadore.camunda.client.dto.ApplicationDto;
import uz.salvadore.camunda.client.entity.ApplicationEntity;

import javax.annotation.PostConstruct;

import static org.modelmapper.Conditions.isNotNull;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationDtoToApplicationEntityMapper {

  ModelMapper mapper;

  @PostConstruct
  public void init() {
    mapper.createTypeMap(ApplicationDto.class, ApplicationEntity.class)
      .addMappings(m -> {
        m.when(isNotNull()).map(ApplicationDto::getCustomer, ApplicationEntity::setCustomer);
      });
  }

}
