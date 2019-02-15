import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void negativeLoginTest() {
        String expectedUrl = "https://www.linkedin.com/";

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.Login("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page has some errors");
    }


    @Test
    public void successfulLoginTest() {
        String expectedUrl = "https://www.linkedin.com/";
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.Login("oksana_fluffy@mail.ru", "sraka007");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page has some errors");

   /*
    Assert.assertTrue(profileNavMenuItem.isDisplayed(), "Home page did not load after Login.");*/

    }
}
