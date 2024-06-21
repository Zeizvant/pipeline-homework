package ge.tbc.tbcacademy.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static ge.tbc.tbcacademy.data.Constants.*;

public class TestListener implements ITestListener {
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    @Override
    public void onTestStart(ITestResult result){
        System.out.printf(TEST_METHOD_START_LOG, result.getMethod().getDescription(), dateFormat.format(result.getStartMillis()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf(TEST_METHOD_SUCCESS_LOG, result.getMethod().getDescription(), dateFormat.format(result.getEndMillis()), calculateDurationOfMethods(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.printf(TEST_METHOD_FAILURE_LOG, result.getMethod().getDescription(), dateFormat.format(result.getEndMillis()), calculateDurationOfMethods(result), result.getStatus());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.printf(TEST_METHOD_SKIP_LOG, result.getMethod().getDescription(), dateFormat.format(result.getEndMillis()), calculateDurationOfMethods(result));
    }

    @Override
    public void onStart(ITestContext context){
        System.out.printf(TEST_START_LOG, context.getName(), dateFormat.format(context.getStartDate()));
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.printf(TEST_END_LOG, context.getName(), dateFormat.format(context.getEndDate()), calculateDurationOfTests(context));
    }

    private long calculateDurationOfMethods(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        long duration = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
        return duration;
    }

    private long calculateDurationOfTests(ITestContext context){
        long duration = context.getEndDate().getTime() - context.getStartDate().getTime();
        return TimeUnit.MILLISECONDS.toSeconds(duration);
    }
}
