package moex;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;

// This class is will be extended by other test classes and the BasePageClass
public class AutomationBaseClass {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static final int TIMEOUT = 10;

    @org.testng.annotations.BeforeMethod
    public static void before() {
        String downloadFilepath = System.getProperty("user.dir");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", downloadFilepath);
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setHeadless(false);
        chromeOptions.addArguments("window-size=default");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("pageLoadStrategy", "none");
        chromeOptions.merge(dc);
        final ChromeDriverService service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        driver = new ChromeDriver(service, chromeOptions);
        driver.manage().window().fullscreen();
    }

    @org.testng.annotations.AfterMethod
    public static void after() {
        if (null != driver) {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
        }
    }

    public void WaitForElementToAppear(WebElement element) {
        wait = new WebDriverWait(getDriver(), TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            System.out.println("TimeoutException!!!");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
