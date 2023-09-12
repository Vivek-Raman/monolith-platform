package dev.vivekraman.monolith.platform.config;

import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@ConditionalOnProperty(value = "false")
public class MDCFilter implements WebFilter {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    MDC.put("key", "API-" + UUID.randomUUID().toString().replace("-", ""));
    return chain.filter(exchange).doFinally(signalType -> {
      MDC.clear();
    });
  }
}
