package dev.vivekraman.monolith.platform.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@RetryingTest(MonolithTest.MAX_ATTEMPTS)
@Test
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MonolithTest {
  int MAX_ATTEMPTS = 3;
}
