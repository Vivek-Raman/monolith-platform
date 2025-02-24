package dev.vivekraman.monolith.platform.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class Beans {
  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Bean
  public ExecutorService executorService() {
    return Executors.newSingleThreadExecutor();
  }
}
