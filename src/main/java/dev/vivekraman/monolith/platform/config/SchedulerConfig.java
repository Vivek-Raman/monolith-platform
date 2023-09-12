package dev.vivekraman.monolith.platform.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Data
@ConfigurationProperties(prefix = "scheduler")
public class SchedulerConfig {
  private Integer corePoolSize = 100;
  private Integer maximumPoolSize = 300;
  private Integer keepAliveTime = 60;
  private TimeUnit unit = TimeUnit.SECONDS;
  private Integer maxQueue = 5000;
  private Boolean allowCoreThreadTimeOut = Boolean.TRUE;

  @Bean
  public Scheduler scheduler() throws Exception {
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(getCorePoolSize(), getMaximumPoolSize(),
            getKeepAliveTime(), getUnit(),
            new LinkedBlockingQueue<>(getMaxQueue()));
    threadPoolExecutor.allowCoreThreadTimeOut(getAllowCoreThreadTimeOut());
    return Schedulers.fromExecutorService(threadPoolExecutor);
  }
}
