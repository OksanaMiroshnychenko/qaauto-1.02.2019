package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    /**
     * Constructor for PasswordResetEmailCheck Page.
     *
     * @param driver - WebDriver instance from BaseTest.
     */
    public RequestPasswordResetSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {
        return resendLinkButton.isDisplayed()
                && driver.getCurrentUrl().equals("checkpoint/rp/request-password-reset-submit")
                && driver.getTitle().equals("Проверьте, получили ли вы сообщение эл. почты со ссылкой для изменения пароля. | LinkedIn");
    }


    public ChooseNewPasswordPage navigateToLinkFromEmail() {
        driver.get(resetPasswordUrl);
        return  new ChooseNewPasswordPage(driver);
    }
}
