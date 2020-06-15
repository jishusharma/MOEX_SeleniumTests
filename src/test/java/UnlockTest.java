import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import selenium.sync.SyncClass;

public class UnlockTest {
    private ExtentTest logger;

    @Test
    public void unlockTest() throws InterruptedException {
        logger = CommonSuite.extent.createTest("passTest");
        Thread.sleep(30000);

        System.out.println("Call notify from other thread");
        logger.log(Status.PASS, "Call notify from other thread");
        SyncClass.syncTest(false);
    }

}
