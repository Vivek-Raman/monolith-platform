package dev.vivekraman.monolith.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "dev.vivekraman.*")
public class MonolithPlatform {
	public static void main(String[] args) {
		SpringApplication.run(MonolithPlatform.class, args);
	}
}
