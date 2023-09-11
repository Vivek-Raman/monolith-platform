package dev.vivekraman.monolith.platform.config;

import org.slf4j.MDC;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class MDCFilter implements WebFilter {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    MDC.put("key", "API-" + UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY));

  }
}
