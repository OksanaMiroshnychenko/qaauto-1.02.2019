package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

/**
 * Class that contains set of reset password tests.
 */
public class ResetPasswordTests extends BaseTest {

    /**
     * Verifies successful password reset functionality.
     */
    @Test
    public void successfulPasswordResetTest() {
        String userEmail = "daemonic.k1tty@gmail.com";

        RequestPasswordResetPage requestPasswordResetPage = landingPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "RequestPasswordReset page is not loaded.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "RequestPasswordResetSubmit page is not loaded.");

        ChooseNewPasswordPage chooseNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(chooseNewPasswordPage.isPageLoaded(), "ChooseNewPassword page is not loaded");

        PasswordChangedPage passwordChangedPage = chooseNewPasswordPage.changePassword("sraka009", "sraka009");
        Assert.assertTrue(passwordChangedPage.isPageLoaded(), "PasswordChanged page is not loaded");

        HomePage homePage = passwordChangedPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");

    }
}
