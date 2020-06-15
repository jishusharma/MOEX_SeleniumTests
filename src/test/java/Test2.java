import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import selenium.AutomationBaseClass;
import selenium.pages.SeleniumPage;
import selenium.sync.SyncClass;


public class Test2 {
    private ExtentTest logger;
    private static SeleniumPage seleniumPage;
    private static ChromeDriver driver;

    @org.testng.annotations.BeforeMethod
    public static void init() {
        driver = new ChromeDriver();
        seleniumPage = new SeleniumPage(driver);
    }

    @Test
    public void test2() {
        logger = CommonSuite.extent.createTest("passTest2");

        System.out.println("Test 2 assertions 1");
        Assert.assertEquals("assert1ForTest2", "assert1ForTest2");
        logger.log(Status.PASS, "Test 2 assertions 1");

        driver.get("https://www.selenium.dev/");
        SyncClass.syncTest(true);

        Assert.assertEquals(driver.getTitle(), "SeleniumHQ Browser Automation", "Title check failed!");

        System.out.println("Test 2 assertions 2");
        Assert.assertEquals("assert2ForTest2", "assert2ForTest2");
        logger.log(Status.PASS, "Test 2 assertions 2");
    }

    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }

}
