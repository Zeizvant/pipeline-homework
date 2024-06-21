package ge.tbc.tbcacademy.utils;

import ge.tbc.tbcacademy.annotations.Retry;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
// RetryAnalyzer intestNGtemp branch
// comment in testNGGroupAndRetry branch
/**
 * Implements the IRetryAnalyzer interface to provide a custom retry mechanism
 * for TestNG tests. This analyzer retries failed test methods based on the `maxAttempts`
 * value specified in the Retry annotation.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Retry.class).maxAttempts()) {
                count++;
                return true;
            }
        }
        iTestResult.setStatus(ITestResult.FAILURE);
        return false;
    }
}
