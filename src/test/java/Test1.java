import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import selenium.sync.SyncClass;

public class Test1 {
    private ExtentTest logger;
    
    @Test
    public void test1() {
        logger = CommonSuite.extent.createTest("passTest");
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

}
