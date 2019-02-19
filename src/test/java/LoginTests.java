import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void successfulLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login("oksana_fluffy@mail.ru", "sraka007");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login.");
    }

    @Test
    public void negativeLoginReturnedToLandingTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
    }

    @Test
    public void negativeLoginReturnedToLoginSubmitTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login("oksana_fluffy@mail.ru", "1111");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage is not loaded.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(), "Это неверный пароль. Повторите попытку или измените пароль.",
                "Wrong validation message for password field.");

    }

}
