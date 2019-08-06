package moex.tests;

import moex.AutomationBaseClass;
import moex.pages.LoginPage;
import moex.pages.UserHomePage;
import org.testng.Assert;
import java.text.ParseException;
import java.time.LocalDateTime;

public class UserHomePage_Test extends AutomationBaseClass {
    private static LoginPage loginPage;
    private static UserHomePage userHomePage;

    @org.testng.annotations.BeforeMethod
    public void init() {
        loginPage = new LoginPage(getDriver());
    }

    @org.testng.annotations.Test(priority = 3)
    public void test_switchToWorkStation() {
        loginPage.goToLoginPage();
        userHomePage = loginPage.loginWithCorrectCredentials(loginPage.username, loginPage.password);
        WaitForElementToAppear(userHomePage.userImage);
        userHomePage.switchToWorkStation();
        Assert.assertTrue(userHomePage.traderCombobox.isDisplayed(),"Trader not displayed!");

    }

    //This test will fail if no records are found for the current date
    @org.testng.annotations.Test(priority = 4)
    public void test_getDealsForToday() throws ParseException {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59, 59);
        getDeals(startDate, endDate);
        Assert.assertTrue(userHomePage.doesTableHasRows());
    }

    //This test has been additionally written as the current date test was not fetching any results to assert for
    @org.testng.annotations.Test(priority = 5)
    public void c_test_getDealsLastMonth() throws ParseException {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.of(date.getYear(), date.getMonth().minus(1), date.getDayOfMonth(), 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59, 59);
        getDeals(startDate, endDate);
        Assert.assertTrue(userHomePage.doesTableHasRows());
        userHomePage.saveFile();
    }

    private void getDeals(LocalDateTime startDate, LocalDateTime endDate) throws ParseException {
        loginPage.goToLoginPage();
        userHomePage = loginPage.loginWithCorrectCredentials(loginPage.username, loginPage.password);
        WaitForElementToAppear(userHomePage.userImage);
        userHomePage.switchToWorkStation();
        userHomePage.getDealsForDateRange(startDate, endDate);
    }
}