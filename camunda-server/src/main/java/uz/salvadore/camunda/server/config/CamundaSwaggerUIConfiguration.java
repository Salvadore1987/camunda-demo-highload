package uz.salvadore.camunda.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CamundaSwaggerUIConfiguration implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/swaggerui/**")
      .addResourceLocations("classpath:/swaggerui/", "classpath:openapi.json");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // Map /swaggerui and /swaggerui/ to the index.html directly
    registry.addRedirectViewController("/swaggerui", "/swaggerui/");
    registry.addViewController("/swaggerui/").setViewName("forward:/swaggerui/index.html");
  }

}
