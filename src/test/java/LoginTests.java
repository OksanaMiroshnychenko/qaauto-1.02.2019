import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
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
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login(userEmail, userPassword);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login.");
    }

    @DataProvider
    public Object[][] invalidData() {
        return new Object[][]{
                {"a@b.c", ""},
                {"ab.c", ""},
                {"", "1"}
        };
    }

    @Test(dataProvider = "invalidData")
    public void negativeLoginReturnedToLandingTest(String userEmail, String userPassword) {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login(userEmail, userPassword);

        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
    }

    @DataProvider
    public Object[][] incorrectData() {
        return new Object[][]{
                {"oksana_fluffy@mail.ru", "1111", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                {"oksana_fluffy@mail", "sraka007", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"6666666", "sraka007", "Обязательно включите в номер значок «+» и код своей страны.", ""}
        };
    }

    @Test(dataProvider = "incorrectData")
    public void negativeLoginReturnedToLoginSubmitTest(String userEmail, String userPassword, String emailValidationMessage, String passwordValidationMessage) {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login(userEmail, userPassword);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage is not loaded.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(), passwordValidationMessage,
                "Wrong validation message for password field.");
        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(), emailValidationMessage,
                "Wrong validation message for login field.");

    }


    @Test
    public void negativeLoginIncorrectEmailEmptyPasswordTest() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.Login("ab.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

    }

    @Test
    public void negativeLoginEmptyLoginTest() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
        landingPage.Login("", "1");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

    }

    @Test
    public void negativeIncorrectLoginCorrectPasswordTest() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
        landingPage.Login("oksana_fluffy@mail", "sraka007");

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage is not loaded.");
        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(), "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.",
                "Wrong validation message for login field.");

    }

}
