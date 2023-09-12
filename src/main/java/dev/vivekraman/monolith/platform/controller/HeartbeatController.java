package dev.vivekraman.monolith.platform.controller;

import dev.vivekraman.monolith.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequiredArgsConstructor
public class HeartbeatController {
  private final Scheduler scheduler;

  @GetMapping("/asd")
  Mono<Response<Boolean>> alive() {
    return Mono.just(Response.of(true))
        .subscribeOn(scheduler);
  }
}
