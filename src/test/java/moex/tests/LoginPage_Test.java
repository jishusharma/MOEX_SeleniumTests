package moex.tests;

import moex.AutomationBaseClass;
import moex.pages.LoginPage;
import moex.pages.UserHomePage;
import org.testng.Assert;


//Test class with tests that will test the functionalities on the LoginPage
public class LoginPage_Test extends AutomationBaseClass {
    private static LoginPage loginPage;
    private static UserHomePage userHomePage;

    @org.testng.annotations.BeforeMethod
    public void init() {
        loginPage = new LoginPage(getDriver());
    }

    @org.testng.annotations.Test(priority = 1)
    public void test_incorrect_login() {
        Assert.assertTrue(true);
        loginPage.goToLoginPage();
        loginPage.loginToApplication(loginPage.username, loginPage.incorrectPassword);
        WaitForElementToAppear(loginPage.incorrectLoginErrorMessage);
        Assert.assertEquals("Такая комбинация логин-пароль не найдена", loginPage.incorrectLoginErrorMessage.getText());
    }

    @org.testng.annotations.Test(priority = 2)
    public void test_correct_login() {
        Assert.assertTrue(true);
        loginPage.goToLoginPage();
        userHomePage = loginPage.loginWithCorrectCredentials(loginPage.username, loginPage.password);
        WaitForElementToAppear(userHomePage.userImage);
        Assert.assertTrue(userHomePage.userImage.isDisplayed(), "User Image not found!");
    }
}
