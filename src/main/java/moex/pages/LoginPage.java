package moex.pages;

import moex.BasePageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageClass {

    public static final String username = "test08";
    public static final String password = "1q2w3e4r";
    public static final String incorrectPassword = "incorrect";

    @FindBy(xpath = "//div[@class='z-logo']")
    public static WebElement zLogo;

    @FindBy(xpath = "//input[@name='llogin']")
    public static WebElement txt_login;

    @FindBy(xpath = "//input[@name='ppasword']")
    public static WebElement txt_password;

    @FindBy(xpath = "//button[@class='z-cropbuttonn']")
    public static WebElement btn_login;

    @FindBy(xpath = "//div[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']/span[@class='z-label']")
    public static WebElement incorrectLoginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {
        driver.navigate().to("https://urozhay-test.moex.com/harvestWeb/");
        WaitForElementToAppear(zLogo);
    }

    public void loginToApplication(String username, String password) {
        txt_login.sendKeys(username);
        txt_password.sendKeys(password);
        btn_login.click();
    }

    public UserHomePage loginWithCorrectCredentials(String username, String password) {
        txt_login.sendKeys(username);
        txt_password.sendKeys(password);
        btn_login.click();
        return new UserHomePage();
    }
}
