package moex.tests;

import moex.AutomationBaseClass;
import moex.pages.LoginPage;
import moex.pages.UserHomePage;
import org.testng.Assert;


//Test class with tests that will test the functionalities on the LoginPage
public class LoginPage_Test extends AutomationBaseClass {
    private static LoginPage loginPage;

    @org.testng.annotations.BeforeMethod
    public void init() {
        loginPage = new LoginPage(getDriver());
    }

    @org.testng.annotations.Test(priority = 1)
    public void test_incorrect_login() {
        Assert.assertTrue(true);
        loginPage.goToLoginPage();
        loginPage.loginToApplication(LoginPage.username, LoginPage.incorrectPassword);
        WaitForElementToAppear(LoginPage.incorrectLoginErrorMessage);
        Assert.assertEquals("Такая комбинация логин-пароль не найдена", LoginPage.incorrectLoginErrorMessage.getText());
    }

    @org.testng.annotations.Test(priority = 2)
    public void test_correct_login() {
        Assert.assertTrue(true);
        loginPage.goToLoginPage();
        loginPage.loginWithCorrectCredentials(LoginPage.username, LoginPage.password);
        WaitForElementToAppear(UserHomePage.userImage);
        Assert.assertTrue(UserHomePage.userImage.isDisplayed(), "User Image not found!");
    }
}
