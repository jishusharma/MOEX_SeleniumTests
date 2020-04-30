import org.testng.Assert;
import org.junit.Test;
import selenium.sync.SyncClass;

public class Test2 {

    @Test
    public void test2() {
        System.out.println("Test 2 assertions 1");
        Assert.assertEquals("assert1ForTest2", "assert1ForTest2");

        SyncClass.syncTest(true);

        System.out.println("Test 2 assertions 2");
        Assert.assertEquals("assert2ForTest2", "assert2ForTest2");
    }

}
