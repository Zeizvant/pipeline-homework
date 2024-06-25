package ge.tbc.tbcacademy.config;

import static com.codeborne.selenide.AssertionMode.SOFT;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.SoftAsserts;
import ge.tbc.tbcacademy.listeners.ReportListener;
import ge.tbc.tbcacademy.listeners.SuiteListener;
import ge.tbc.tbcacademy.listeners.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static ge.tbc.tbcacademy.data.Constants.INVALID_BROWSER_PARAMETER_MESSAGE;

@Listeners({TestListener.class, SuiteListener.class, ReportListener.class, SoftAsserts.class})
public class ConfigTests {
    WebDriver driver;
    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        String token = System.getenv("Token");

        if (token != null) {
            System.out.println("Test running in Azure DevOps CI/CD, using key1: " + token);

            // Additional Chrome options for running in Docker
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);
        } else {
            System.out.println("Test running locally");

            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        Configuration.timeout = 3000;
        Configuration.assertionMode = SOFT;
        WebDriverRunner.setWebDriver(driver);
    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        closeWebDriver();
    }

}
