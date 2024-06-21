package ge.tbc.tbcacademy.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;

import static ge.tbc.tbcacademy.data.Constants.REPORT_FAILED_TESTS;

public class ReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        System.out.println(REPORT_FAILED_TESTS);
        suites.stream() // Stream over suites
                .flatMap(suite -> suite.getResults().values().stream()) // Stream over suite results
                .map(ISuiteResult::getTestContext)
                .map(ITestContext::getFailedTests)
                .map(IResultMap::getAllMethods)
                .filter(methods -> !methods.isEmpty()) // Keep only if there are failed methods
                .forEach(methods -> methods.forEach(failedMethod ->
                                System.out.println(failedMethod.getDescription())
                        )
                );
    }
}
