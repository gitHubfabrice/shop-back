package com.fatechnologies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zalando.problem.jackson.ProblemModule;

import java.util.List;

/**
 * <p>Adaptateur de l'interface de configuration {@link WebMvcConfigurer}.</p>
 *
 * @author Christian Amani 2022-02-04
 */
@Configuration
@EnableAsync
@EnableScheduling
public class BillingWebMvcConfigureAdapter implements WebMvcConfigurer {


  private final MappingJackson2HttpMessageConverter messageConverters;

  public BillingWebMvcConfigureAdapter(
      MappingJackson2HttpMessageConverter messageConverters) {
    this.messageConverters = messageConverters;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    this.messageConverters.getObjectMapper()
        .registerModule(new ProblemModule().withStackTraces(false));
    converters.add(messageConverters);
    converters.add(new ResourceHttpMessageConverter());
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
