import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {
    WebDriver driver;

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

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/login-submit")
                && loginForm.isDisplayed()
                && driver.getTitle().contains("Войти в LinkedIn");
    }

    public String getPasswordValidationMessageText() {
        return passwordValidationMessage.getText();
    }

    public String getEmailValidationMessageText() {
        return emailValidationMessage.getText();
    }
}
