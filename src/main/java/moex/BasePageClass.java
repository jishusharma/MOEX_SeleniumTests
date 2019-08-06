package moex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

//This class has the Pagefactory method. The actual application pages will extend to this class.
public class BasePageClass extends AutomationBaseClass {
    public BasePageClass(WebDriver finalDriver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(finalDriver, TIMEOUT), this);
    }
}
