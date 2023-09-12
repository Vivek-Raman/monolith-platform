package dev.vivekraman.monolith.platform.controller;

import dev.vivekraman.monolith.platform.config.SchedulerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequiredArgsConstructor
public class HeartbeatController {
  private final Scheduler scheduler;

  @GetMapping
  Mono<Boolean> alive() {
    return Mono.just(true)
        .subscribeOn(scheduler);
  }
}
