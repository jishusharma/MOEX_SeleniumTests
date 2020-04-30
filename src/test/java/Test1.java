import org.testng.Assert;
import org.junit.Test;
import selenium.sync.SyncClass;

public class Test1 {

    @Test
    public void test1() {
        System.out.println("Test 1 assertions 1");
        Assert.assertEquals("assert1ForTest1", "assert1ForTest1");

        SyncClass.syncTest(true);

        System.out.println("Test 1 assertions 2");
        Assert.assertEquals("assert2ForTest1", "assert2ForTest1");
    }

}
