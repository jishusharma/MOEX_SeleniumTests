import org.testng.annotations.Test;
import selenium.sync.SyncClass;

public class UnlockTest {

    @Test
    public void unlockTest() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Call notify from other thread");
        SyncClass.syncTest(false);
    }

}
