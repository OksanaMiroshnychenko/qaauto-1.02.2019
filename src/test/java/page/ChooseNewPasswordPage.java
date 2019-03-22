package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for PasswordResetRequestSubmission Page.
 */
public class ChooseNewPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor for PasswordResetRequestSubmission Page.
     * @param driver - WebDriver instance from BaseTest.
     */
    public ChooseNewPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/checkpoint/rp/password-reset?requestSubmissionId=")
                && submitButton.isDisplayed()
                && driver.getTitle().equals("Изменить пароль | LinkedIn");
    }

    /**
     * Method that changes user password.
     * @param newPassword - new password string
     * @param confirmPassword - confirm password string
     * @return - new instance of Home page class.
     */
    public PasswordChangedPage changePassword(String newPassword, String confirmPassword) {
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(confirmPassword);
        submitButton.click();
        return new PasswordChangedPage(driver);
    }


}
