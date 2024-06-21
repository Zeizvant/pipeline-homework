package ge.tbc.tbcacademy.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
// comment in test testNGtemp branch
// comment on testNGGroupAndRetry branch
/**
 * Defines a retry policy for a test method.
 * should be retried a specified number of times if it fails.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    int maxAttempts() default 0;
}
