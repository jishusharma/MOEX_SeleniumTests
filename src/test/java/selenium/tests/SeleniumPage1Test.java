package selenium.tests;

import selenium.AutomationBaseClass;
import selenium.pages.SeleniumPage;
import org.testng.Assert;
import org.testng.annotations.Test;


//Test class with tests that will test the functionalities on the LoginPage
public class SeleniumPage1Test extends AutomationBaseClass {
    private static SeleniumPage seleniumPage;

    @org.testng.annotations.BeforeMethod
    public static void init() {
        seleniumPage = new SeleniumPage(getDriver());
    }

    @Test
    public void a_homepage_Title_Check_Test() {
        seleniumPage.goToHome();
        Assert.assertEquals(getDriver().getTitle(), "SeleniumHQ Browser Automation", "Title check failed!");
    }

    @Test
    public void c_required_Text_Displayed_With_Invalid_Keyword() {
        String searchFor = "SeleniumFake";
        seleniumPage.goToHome();
        seleniumPage.enterAndSearch(searchFor);
        Assert.assertTrue(seleniumPage.searchResultIsEmpty(searchFor), "Nothing must be found for " + searchFor);
    }
}
