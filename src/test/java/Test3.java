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

import static selenium.AutomationBaseClass.getDriver;

public class Test3 {
    private ExtentTest logger;
    private static SeleniumPage seleniumPage;
    private static ChromeDriver driver;

    @org.testng.annotations.BeforeMethod
    public static void init() {
        driver = new ChromeDriver();
        seleniumPage = new SeleniumPage(driver);
    }

    @Test
    public void test3() {
        logger = CommonSuite.extent.createTest("passTest3");

        System.out.println("Test 3 assertions 1");
        Assert.assertEquals("assert1ForTest3", "assert1ForTest3");
        logger.log(Status.PASS, "Test 3 assertions 1");

        driver.get("https://www.selenium.dev/");
        SyncClass.syncTest(true);

        Assert.assertEquals(driver.getTitle(), "SeleniumHQ Browser Automation", "Title check failed!");

        System.out.println("Test 3 assertions 2");
        Assert.assertEquals("assert2ForTest3", "assert2ForTest3");
        logger.log(Status.PASS, "Test 3 assertions 2");
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
