package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for Landing page.
 */
public class LandingPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//*[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor for Landing Page.
     * @param driver - WebDriver instance from BaseTest.
     */
    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method (generic type) that performs login to the site.
     * @param userEmail - userEmail string for login
     * @param userPassword - userPassword string for login
     * @param <T> - Type of generic page
     * @return - instance of the T-page
     */
    public <T> T login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        if (driver.getCurrentUrl().contains("/feed/")) {
            return (T) new HomePage(driver);
        }
        if (driver.getCurrentUrl().contains("/login-submit")) {
            return (T) new LoginSubmitPage(driver);
        } else {
            return (T) new LandingPage(driver);
        }
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && driver.getTitle().equals("LinkedIn: Войти или зарегистрироваться");
    }

    /**
     * Method that click the forgotPassword link.
     * @return - new instance of RequestPasswordReset Page class.
     */
    public RequestPasswordResetPage clickOnForgotPasswordLink() {
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(driver);
    }
}
