package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for PasswordChanged Page.
 */
public class PasswordChangedPage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    WebElement goToHomepageButton;

    /**
     * Constructor for PasswordChanged Page.
     * @param driver
     */
    public PasswordChangedPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * ethod that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return goToHomepageButton.isDisplayed()
                && driver.getCurrentUrl().contains("checkpoint/rp/password-reset-submit")
                && driver.getTitle().equals("Вы изменили свой пароль. | LinkedIn");
    }

    /**
     * Method which redirects to HomePage.
     * @return - new instance of HomePage class.
     */
    public HomePage goToHomepage() {
        goToHomepageButton.click();
        return new HomePage(driver);
    }

}
