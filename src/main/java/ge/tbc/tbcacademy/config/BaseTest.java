package ge.tbc.tbcacademy.config;

import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.tbcacademy.listeners.ReportListener;
import ge.tbc.tbcacademy.listeners.SuiteListener;
import ge.tbc.tbcacademy.listeners.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
    import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static ge.tbc.tbcacademy.data.Constants.*;

@Listeners({TestListener.class, SuiteListener.class, ReportListener.class})
public class BaseTest {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException(INVALID_BROWSER_PARAMETER_MESSAGE);
        }
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}
