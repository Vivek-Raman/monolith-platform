package dev.vivekraman.monolith.platform.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpringdocConfig {
  private static final String API_GROUP = "common";
  @Bean
  public GroupedOpenApi commonApiGroup() {
    return GroupedOpenApi.builder()
        .group(API_GROUP)
        .packagesToScan("dev.vivekraman.monolith.platform.controller")
        .build();
  }

  private static final String ALL_APIS = "all";
  @Bean
  public GroupedOpenApi allApiGroup() {
    return GroupedOpenApi.builder()
        .group(ALL_APIS)
        .packagesToScan("dev.vivekraman")
        .build();
  }

  @Value("${spring.application.version}") private String version;

  @Bean
  public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
    return (openApi) -> {
      Info info = new Info();
      info.setTitle("Vivek Raman's Backend Monolith Platform v2");
      info.setVersion(version);
      info.setDescription("A collection of APIs that Vivek Raman uses across various projects. \n"
          + "Use the definitions on the top-right to pick a project and locate its APIs.");

      Contact contact = new Contact();
      contact.setName("Vivek Raman");
      contact.setEmail("vr.ac4bf@live.com");
      contact.setUrl("https://vivekraman.dev");
      info.setContact(contact);

      openApi.setInfo(info);
    };
  }
}
