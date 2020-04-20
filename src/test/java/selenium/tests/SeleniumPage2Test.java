package selenium.tests;

import selenium.AutomationBaseClass;
import selenium.pages.SeleniumPage;
import org.testng.Assert;
import org.testng.annotations.Test;


//Test class with tests that will test the functionalities on the LoginPage
public class SeleniumPage2Test extends AutomationBaseClass {
    private static SeleniumPage seleniumPage;

    @org.testng.annotations.BeforeMethod
    public void init() {
        seleniumPage = new SeleniumPage(getDriver());
    }

    @Test
    public void a_homepage_Title_Check_Test() {
        seleniumPage.goToHome();
        Assert.assertEquals(getDriver().getTitle(), "SeleniumHQ Browser Automation", "Title check failed!");
    }

    @Test
    public void b_results_Displayed_With_Valid_Keyword() {
        seleniumPage.goToHome();
        String searchFor = "Selenium";
        seleniumPage.enterAndSearch(searchFor);
        Assert.assertTrue(seleniumPage.searchResultsDisplayed(searchFor), "No Result for  " + searchFor);
    }

    @Test
    public void c_required_Text_Displayed_With_Invalid_Keyword() {
        seleniumPage.goToHome();
        String searchFor = "SeleniumFake";
        seleniumPage.enterAndSearch(searchFor);
        Assert.assertTrue(seleniumPage.searchResultIsEmpty(searchFor), "Nothing must be found for " + searchFor);
    }
}
