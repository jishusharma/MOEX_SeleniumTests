package moex.pages;

import moex.BasePageClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SeleniumPage extends BasePageClass {

    @FindBy(xpath = "/html//input[@id='gsc-i-id1']")
    private WebElement searchBox;

    @FindBy(xpath = "/html//b[.='Selenium']")
    private WebElement seleniumText;

    @FindBy(xpath = "/html//div[@class='gs-snippet']")
    private WebElement noSearchResult;


    public SeleniumPage(WebDriver driver) {
        super(driver);
    }

    public void goToHome() {
        driver.navigate().to("https://www.selenium.dev/");
    }

    public void enterAndSearch(String searchText) {
        clearAndSendKeys(searchBox, searchText);
        searchBox.sendKeys(Keys.ENTER);

    }

    public boolean searchResultsDisplayed(String searchText) {
        try {
            waitForElement(seleniumText);
        } catch (Exception e) {
        }
        return seleniumText.isDisplayed()
                && seleniumText.getText().startsWith(searchText)
                && seleniumText.getText().endsWith(searchText);
    }

    public boolean searchResultIsEmpty(String searchText) {
        try {
            waitForElement(noSearchResult);

        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
        return noSearchResult.isDisplayed();
    }
}
