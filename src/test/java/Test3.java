import org.testng.Assert;
import org.junit.Test;
import selenium.sync.SyncClass;

public class Test3 {

    @Test
    public void test3() {
        System.out.println("Test 3 assertions 1");
        Assert.assertEquals("assert1ForTest3", "assert1ForTest3");

        SyncClass.syncTest(true);

        System.out.println("Test 3 assertions 2");
        Assert.assertEquals("assert2ForTest3", "assert2ForTest3");
    }
}
