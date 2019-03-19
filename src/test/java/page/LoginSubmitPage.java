package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object class for Login submit page.
 */
public class LoginSubmitPage extends BasePage {

    @FindBy(xpath = "//form[@class='login__form']")
    WebElement loginForm;

    @FindBy(xpath = "//div[@id='error-for-password']")
    WebElement passwordValidationMessage;

    @FindBy(xpath = "//div[@id='error-for-username']")
    WebElement emailValidationMessage;
    
    public LoginSubmitPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/login-submit")
                && loginForm.isDisplayed()
                && driver.getTitle().contains("Войти в LinkedIn");
    }

    /**
     * Gets string of password validation message.
     * @return password validation message text
     */
    public String getPasswordValidationMessageText() {
        return passwordValidationMessage.getText();
    }

    /**
     * Gets string of email validation message.
     * @return email validation message text
     */
    public String getEmailValidationMessageText() {
        return emailValidationMessage.getText();
    }
}
