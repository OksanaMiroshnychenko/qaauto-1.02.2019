package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object class for BasePage.
 */
public class BasePage {
    protected WebDriver driver;
    protected static String resetPasswordUrl;

    protected void waitUntilElementIsClickable(WebElement webElement, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilElementIsClickable(WebElement webElement) {
        waitUntilElementIsClickable(webElement, 5);
    }

}
