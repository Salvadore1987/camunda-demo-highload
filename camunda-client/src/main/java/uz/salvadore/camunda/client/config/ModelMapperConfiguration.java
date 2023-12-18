package uz.salvadore.camunda.client.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ModelMapperConfiguration {

  public static void configMapper(final ModelMapper modelMapper) {
    modelMapper.getConfiguration()
      .setMethodAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PUBLIC)
      .setMatchingStrategy(MatchingStrategies.STRICT)
      .setFieldMatchingEnabled(Boolean.TRUE)
      .setSkipNullEnabled(Boolean.TRUE)
      .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  public ModelMapper modelMapper() {
    final ModelMapper mapper = new ModelMapper();
    mapper.validate();
    configMapper(mapper);
    return mapper;
  }


}
