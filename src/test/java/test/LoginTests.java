package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

/**
 * Contains set of valid credentials for successful login scenario.
 */
public class LoginTests extends BaseTest{

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                {"oksana_fluffy@mail.ru", "sraka007"},
                //{"oksana_FLUFFY@mail.ru", "sraka007"},
                //{" oksana_fluffy@mail.ru ", "sraka007"}
        };
    }

    /**
     * @param userEmail - user email string for login
     * @param userPassword - user password string for login
     * Login with correct credentials to https://www.linkedin.com/, verify that HomePage is loaded.
     */
    @Test(dataProvider = "validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login.");
    }

    @DataProvider
    public Object[][] wrongData() {
        return new Object[][]{
                {"a@b.c", ""},
               // {"ab.c", ""},
                //{"", "1"}
        };
    }

    @Test(dataProvider = "wrongData")
    public void negativeLoginReturnedToLandingTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        landingPage.login(userEmail, userPassword);

        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidData() {
        return new Object[][]{
                {"oksana_fluffy@mail.ru", "1111", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                //{"oksana_fluffy@mail", "sraka007", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                //{"6666666", "sraka007", "Обязательно включите в номер значок «+» и код своей страны.", ""}
        };
    }

    /**
     * Method checks login scenario with incorrect data.
     * @param userEmail - user email string for login
     * @param userPassword - user password string for login
     * @param emailValidationMessage - user email validation message
     * @param passwordValidationMessage - user password validation message
     */
    @Test(dataProvider = "invalidData")
    public void negativeLoginReturnedToLoginSubmitTest(String userEmail,
                                                       String userPassword,
                                                       String emailValidationMessage,
                                                       String passwordValidationMessage) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        LoginSubmitPage loginSubmitPage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "page.LoginSubmitPage is not loaded.");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(), emailValidationMessage,
                "Wrong validation message for email field.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(), passwordValidationMessage,
                "Wrong validation message for password field.");
    }

}
