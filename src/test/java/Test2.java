import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.sync.SyncClass;

public class Test2 {
    static ExtentLoggerReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest logger;

    @BeforeClass
    public static void startTest()
    {
        htmlReporter = new ExtentLoggerReporter(System.getProperty("user.dir") +"/test-output/test2/");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @Test
    public void test2() {
        logger = extent.createTest("passTest2");

        System.out.println("Test 2 assertions 1");
        Assert.assertEquals("assert1ForTest2", "assert1ForTest2");
        logger.log(Status.PASS, "Test 2 assertions 1");

        SyncClass.syncTest(true);

        System.out.println("Test 2 assertions 2");
        Assert.assertEquals("assert2ForTest2", "assert2ForTest2");
        logger.log(Status.PASS, "Test 2 assertions 2");
    }

    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }

    @AfterClass
    public static void endTest()
    {
        extent.flush();
    }
}
