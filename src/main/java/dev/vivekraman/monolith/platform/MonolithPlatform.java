package dev.vivekraman.monolith.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "dev.vivekraman.*",
  exclude = {
    ReactiveSecurityAutoConfiguration.class,
    ReactiveUserDetailsServiceAutoConfiguration.class,
  })
public class MonolithPlatform {
	public static void main(String[] args) {
		SpringApplication.run(MonolithPlatform.class, args);
	}
}
