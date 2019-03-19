package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetEmailCheckPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 'different__email different__email--desktop')]")
    private WebElement tryDifferentEmailButton;

    /**
     * Constructor for PasswordResetEmailCheck Page.
     *
     * @param driver - WebDriver instance from BaseTest.
     */
    public PasswordResetEmailCheckPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {
        return tryDifferentEmailButton.isDisplayed()
                && driver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
                && driver.getTitle().equals("Проверьте, получили ли вы сообщение эл. почты со ссылкой для изменения пароля. | LinkedIn");
    }



    public PasswordResetEmailCheckPage followLink() {
        return new PasswordResetEmailCheckPage(driver);
    }
}
