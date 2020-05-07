import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.sync.SyncClass;

public class Test3 {
    private ExtentTest logger;

    @Test
    public void test3() {
        logger = CommonSuite.extent.createTest("passTest3");

        System.out.println("Test 3 assertions 1");
        Assert.assertEquals("assert1ForTest3", "assert1ForTest3");
        logger.log(Status.PASS, "Test 3 assertions 1");

        SyncClass.syncTest(true);

        System.out.println("Test 3 assertions 2");
        Assert.assertEquals("assert2ForTest3", "assert2ForTest3");
        logger.log(Status.PASS, "Test 3 assertions 2");
    }
}
