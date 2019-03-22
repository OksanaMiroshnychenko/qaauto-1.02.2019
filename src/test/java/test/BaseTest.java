package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LandingPage;

/**
 * Basic class for all test classes.
 */
public class BaseTest {
    private WebDriver driver;
    LandingPage landingPage;

    /**
     * Runs before each test and performs necessary actions (initializes ChromeDriver, goes to linkedin.com page, initializes new instance of landingPage).
     */
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LandingPage(driver);
    }

    /**
     * Runs after each test and closes browser, quits driver.
     */
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
