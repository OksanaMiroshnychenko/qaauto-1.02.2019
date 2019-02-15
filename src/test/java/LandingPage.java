import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    WebDriver driver;

    WebElement signInButton;
    WebElement userEmailField;
    WebElement userPasswordField;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
    }

    public void Login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
    }

    public boolean isPageLoaded() {
        if ((driver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")) && (signInButton.isDisplayed())) {
        //if ((driver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")) && (signInButton.isDisplayed()) && (driver.getCurrentUrl().equals("https://www.linkedin.com"))) {
            return true;
        } else {
            return false;
        }
    }
}
