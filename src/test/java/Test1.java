import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.sync.SyncClass;

public class Test1 {
    static ExtentLoggerReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest logger;

    @BeforeClass
    public static void startTest()
    {
        htmlReporter = new ExtentLoggerReporter(System.getProperty("user.dir") +"/test-output/test1/");
        extent = new ExtentReports ();
        extent.attachReporter(htmlReporter);
        /*extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Rajkumar SM");

        htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
        htmlReporter.config().setReportName("Name of the Report Comes here"); */
        htmlReporter.config().setTheme(Theme.STANDARD);
    }
    
    @Test
    public void test1() {
        logger = extent.createTest("passTest");
        System.out.println("Test 1 assertions 1");
        Assert.assertEquals("assert1ForTest1", "assert1ForTest1");
        logger.log(Status.PASS, "Test 1 assertions 1");

        SyncClass.syncTest(true);

        System.out.println("Test 1 assertions 2");
        Assert.assertEquals("assert2ForTest1", "assert2ForTest1");
        logger.log(Status.PASS, "Test 1 assertions 2");
    }

    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            //logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
            //MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }else if(result.getStatus() == ITestResult.SKIP){
            //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }

    @AfterClass
    public static void endTest()
    {
        extent.flush();
    }

}
