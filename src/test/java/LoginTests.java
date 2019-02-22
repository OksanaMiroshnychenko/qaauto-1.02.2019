import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;
    LandingPage landingPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                {"oksana_fluffy@mail.ru", "sraka007"},
                {"oksana_FLUFFY@mail.ru", "sraka007"},
                {" oksana_fluffy@mail.ru ", "sraka007"}
        };
    }

    @Test(dataProvider = "validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        HomePage homePage = landingPage.loginToHomePage(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login.");
    }

    @DataProvider
    public Object[][] wrongData() {
        return new Object[][]{
                {"a@b.c", ""},
                {"ab.c", ""},
                {"", "1"}
        };
    }

    @Test(dataProvider = "wrongData")
    public void negativeLoginReturnedToLandingTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.loginToLandingPage(userEmail, userPassword);

        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidData() {
        return new Object[][]{
                {"oksana_fluffy@mail.ru", "1111", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                {"oksana_fluffy@mail", "sraka007", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"6666666", "sraka007", "Обязательно включите в номер значок «+» и код своей страны.", ""}
        };
    }

    @Test(dataProvider = "invalidData")
    public void negativeLoginReturnedToLoginSubmitTest(String userEmail,
                                                       String userPassword,
                                                       String emailValidationMessage,
                                                       String passwordValidationMessage) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        LoginSubmitPage loginSubmitPage = landingPage.loginToLoginSubmitPage(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage is not loaded.");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(), emailValidationMessage,
                "Wrong validation message for email field.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(), passwordValidationMessage,
                "Wrong validation message for password field.");
    }

}
