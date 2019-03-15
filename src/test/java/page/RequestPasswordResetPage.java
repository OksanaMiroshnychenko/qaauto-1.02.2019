package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestPasswordResetPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;


    public  RequestPasswordResetPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return findAccountButton.isDisplayed()
                && driver.getCurrentUrl().contains("uas/request-password-reset")
                && driver.getTitle().equals("Изменить пароль | LinkedIn");
    }

    public void findAccount(String userEmail) {
        userEmailField.sendKeys(userEmail);

        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = userEmail;
        String messageFrom = "security-noreply@linkedin.com";

        GMailService gMailService = new GMailService();
        gMailService.connect();

        findAccountButton.click();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordUrl = null;
        driver.get(resetPasswordUrl);


    }
}
