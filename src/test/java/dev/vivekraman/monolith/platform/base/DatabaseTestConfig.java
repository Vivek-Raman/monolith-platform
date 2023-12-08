package dev.vivekraman.monolith.platform.base;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@TestConfiguration
@EnableR2dbcRepositories
public class DatabaseTestConfig {
}
