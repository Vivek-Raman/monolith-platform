package dev.vivekraman.monolith.platform.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Configuration
@OpenAPIDefinition(servers = @Server(url = "/", description = "Server URL"))
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

  @Bean
  public OperationCustomizer operationCustomizer() {
    return ((operation, handlerMethod) -> {
      StringBuilder finalPath = new StringBuilder();
      String baseUrl = "https://monolith-platform-v1.ue.r.appspot.com";
      finalPath.append(baseUrl);

      RequestMapping controllerConfig = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
      RequestMapping requestConfig = handlerMethod.getMethodAnnotation(RequestMapping.class);

      finalPath.append(extractPath(controllerConfig));
      finalPath.append(extractPath(requestConfig));

      operation.addExtension("x-google-backend",
          Collections.singletonMap("address", finalPath.toString()));
      return operation;
    });
  }

  private String extractPath(RequestMapping requestMapping) {
    return Optional.ofNullable(requestMapping)
        .map(request -> {
          if (request.path().length > 0) return request.path();
          else return request.value();
        })
        .filter(paths -> paths.length > 0)
        .map(paths -> paths[0])
        .orElse(StringUtils.EMPTY);
  }
}
