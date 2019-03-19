package page;

import bsh.StringUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

/**
 * Page Object class for RequestPasswordReset page.
 */
public class RequestPasswordResetPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;


    /**
     * Constructor for RequestPasswordReset Page.
     * @param driver - WebDriver instance from BaseTest.
     */
    public  RequestPasswordResetPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return findAccountButton.isDisplayed()
                && driver.getCurrentUrl().contains("uas/request-password-reset")
                && driver.getTitle().equals("Изменить пароль | LinkedIn");
    }

    /**
     * Method verifies the account validity.
     * @param userEmail - userEmail string
     * @return - new instance of PasswordResetRequestSubmission page
     */
    public PasswordResetRequestSubmissionPage findAccount(String userEmail) {
        userEmailField.sendKeys(userEmail);

        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = userEmail;
        String messageFrom = "security-noreply@linkedin.com";

        GMailService gMailService = new GMailService();
        gMailService.connect();

        findAccountButton.click();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        int link = message.indexOf("https://www.linkedin.com/e/");
        //StringBuilder sb = StringUtil.borrowBuilder().append(start);

        //text.replaceAll("&amp;", "&");


        //String resetPasswordUrl = null;
        //driver.get(resetPasswordUrl);


    }


}
