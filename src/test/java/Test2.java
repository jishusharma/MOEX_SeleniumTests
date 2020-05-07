import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import selenium.sync.SyncClass;

public class Test2 {
    private ExtentTest logger;

    @Test
    public void test2() {
        logger = CommonSuite.extent.createTest("passTest2");

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

}
