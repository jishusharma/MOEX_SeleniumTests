package moex.tests;

import moex.AutomationBaseClass;
import moex.pages.LoginPage;
import moex.pages.UserHomePage;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;

public class UserHomePage_Test extends AutomationBaseClass {
    private static LoginPage loginPage;
    private static UserHomePage userHomePage;
    private static final String LIST_DEALS_FILENAME = "list_deals.csv";

    @org.testng.annotations.BeforeMethod
    public void init() {
        loginPage = new LoginPage(getDriver());
        getListDealsFile().delete();
    }

    @org.testng.annotations.Test(priority = 3)
    public void test_switchToWorkStation() {
        loginPage.goToLoginPage();
        userHomePage = loginPage.loginWithCorrectCredentials(LoginPage.username, LoginPage.password);
        WaitForElementToAppear(UserHomePage.userImage);
        userHomePage.switchToWorkStation();
        Assert.assertTrue(UserHomePage.traderCombobox.isDisplayed(), "Trader not displayed!");

    }

    //This test will fail if no records are found for the current date
    @org.testng.annotations.Test(priority = 4)
    public void test_getDealsForToday() throws InterruptedException {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59, 59);
        getDeals(startDate, endDate);
        checkDeals();
    }

    //This test has been additionally written as the current date test was not fetching any results to assert for
    @org.testng.annotations.Test(priority = 5)
    public void test_getDealsLastMonth() throws InterruptedException {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.of(date.getYear(), date.getMonth().minus(1), date.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59, 59);
        getDeals(startDate, endDate);
        checkDeals();
    }

    private void getDeals(LocalDateTime startDate, LocalDateTime endDate) {
        loginPage.goToLoginPage();
        userHomePage = loginPage.loginWithCorrectCredentials(LoginPage.username, LoginPage.password);
        WaitForElementToAppear(UserHomePage.userImage);
        userHomePage.switchToWorkStation();
        userHomePage.getDealsForDateRange(startDate, endDate);
    }

    private void checkDeals() throws InterruptedException {
        Assert.assertTrue(userHomePage.doesTableHasRows());
        userHomePage.saveFile();
        Assert.assertTrue(doesFileHasRows(getListDealsFile()));
    }

    private File getListDealsFile() {
        return new File(LIST_DEALS_FILENAME);
    }

    private boolean doesFileHasRows(File file) {
        int headerLines = 2;
        int lines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null && lines <= headerLines) {
                lines++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines > headerLines;
    }
}
