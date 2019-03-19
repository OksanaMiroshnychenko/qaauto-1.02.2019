package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordChangedPage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    WebElement goToHomepageButton;

    public PasswordChangedPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return goToHomepageButton.isDisplayed()
                && driver.getCurrentUrl().contains("checkpoint/rp/password-reset-submit")
                && driver.getTitle().equals("Вы изменили свой пароль. | LinkedIn");
    }

    public HomePage goToHomepage() {
        goToHomepageButton.click();
        return new HomePage(driver);
    }

}
