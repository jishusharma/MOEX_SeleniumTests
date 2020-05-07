import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CommonSuite {

    public static ExtentLoggerReporter htmlReporter;
    public static ExtentReports extent;

    @BeforeSuite
    public void beforeSuite() {
        htmlReporter = new ExtentLoggerReporter(System.getProperty("user.dir") +"/target/test-output/");
        extent = new ExtentReports ();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @Test
    public void test() {
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }

}
