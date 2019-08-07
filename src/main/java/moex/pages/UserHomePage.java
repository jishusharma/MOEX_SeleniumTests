package moex.pages;

import moex.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserHomePage extends BasePageClass {
    @FindBy(xpath = "//img[@title='test08 (Трейдер)']")
    public static WebElement userImage;

    @FindBy(xpath = "//img[@class='logo-switcher z-image']")
    public static WebElement menu;

    @FindBy(xpath = "//i[@class='z-combobox-icon z-icon-caret-down']")
    public static WebElement traderDropDownIcon;

    @FindBy(xpath = "//span[@class='z-comboitem-text']")
    public static WebElement trader;

    @FindBy(xpath = "//input[@class='z-combobox-input']")
    public static WebElement traderCombobox;

    @FindBy(xpath = "//div[@class='menu-item z-caption']/div/div/table[@class='menu-item z-vbox']//table[@class='z-hbox']//img[@class='z-image']")
    public static WebElement marketImageWithOpenMenu;

    @FindBy(xpath = "//img[@title='Рынок']")
    public static WebElement marketImageWithClosedMenu;

    @FindBy(xpath = "//span[contains(text(),'Сделки')]")
    public static WebElement deals;

    @FindBy(xpath = "//tr[1]/td[1]/span[1]/input[1]")
    public static WebElement dateFrom;

    @FindBy(xpath = "//tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[7]/span[1]/input[1]")
    public static WebElement dateTo;

    @FindBy(xpath = "//a[1][span[1]/img[1]]")
    public static WebElement refreshImage;

    @FindBy(xpath = "//div[1]/div[2]/div[3]/table[1]/tbody[1]")
    public static WebElement dealsTable;

    @FindBy(xpath = "//tr[1]/td[13]/a[1]/span[1]/img[1]")
    public static WebElement saveFile;

    public UserHomePage() {
        super(driver);
    }

    public void switchToWorkStation() {
        menu.click();
        WaitForElementToAppear(traderDropDownIcon);
        traderDropDownIcon.click();
        WaitForElementToAppear(trader);
        trader.click();
    }

    public void getDealsForDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            marketImageWithClosedMenu.click();
        } catch (Exception e) {
            marketImageWithOpenMenu.click();
        }
        WaitForElementToAppear(deals);
        deals.click();
        String datePattern = "dd.MM.yyyy HH:mm:ss";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        String d1 = dateFormatter.format(startDate);
        String d2 = dateFormatter.format(endDate);
        dateFrom.clear();
        dateFrom.sendKeys(d1);
        dateTo.clear();
        dateTo.sendKeys(d2);
        dateTo.sendKeys(Keys.TAB);
        WaitForElementToAppear(refreshImage);
        refreshImage.click();
        WaitForElementToAppear(dealsTable);
    }

    public boolean doesTableHasRows() {
        List<WebElement> tableRows = driver.findElements(By.xpath("//div[1]/div[2]/div[3]/table[1]/tbody[1]/tr"));
        System.out.println("Number of records in the table: " + tableRows.size());
        return tableRows.size() > 0;
    }

    public void saveFile() throws InterruptedException {
        saveFile.click();
        Thread.sleep(2000);
    }
}
