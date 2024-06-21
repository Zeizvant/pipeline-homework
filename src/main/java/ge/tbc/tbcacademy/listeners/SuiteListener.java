package ge.tbc.tbcacademy.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static ge.tbc.tbcacademy.data.Constants.*;

public class SuiteListener implements ISuiteListener {
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private Date startDate;
    @Override
    public void onStart(ISuite suite){
        startDate = new Date();
        System.out.printf(SUITE_START_LOG, suite.getName(), dateFormat.format(startDate));
        System.out.println(SUITE_METHODS_HEADER);
        suite.getAllMethods().forEach(method -> {
            System.out.println(String.format(SUITE_EACH_METHOD_STARTER, method.getDescription()));
        });
    }

    @Override
    public void onFinish(ISuite suite){
        long duration = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - startDate.getTime());
        System.out.printf(SUITE_END_LOG ,suite.getName(), dateFormat.format(new Date()), duration);
    }

}
