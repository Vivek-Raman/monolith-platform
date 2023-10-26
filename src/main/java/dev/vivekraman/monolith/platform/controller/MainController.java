package dev.vivekraman.monolith.platform.controller;

import dev.vivekraman.monolith.model.Response;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.TRACE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
  private final Scheduler scheduler;

  @GetMapping
  @Hidden
  public Mono<ResponseEntity<Object>> redirectToSwaggerUI(RequestEntity<?> request) {
    return Mono.just(ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
            .location(request.getUrl().resolve("/swagger-ui.html")).build())
        .subscribeOn(scheduler);
  }


  @RequestMapping(value = "/_ah/warmup", method = {GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS,
      TRACE,})
  @Hidden
  public Mono<Response<Boolean>> warmup(RequestEntity<?> request) {
    log.info("We're up on {}!", request.getUrl().resolve("/swagger-ui.html"));
    return Mono.just(Response.of(true))
        .subscribeOn(scheduler);
  }
}
