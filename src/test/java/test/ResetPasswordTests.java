package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTests extends BaseTest {

    @Test
    public void successfulPasswordResetTest() {
        String userEmail = "daemonic.k1tty@gmail.com";

        RequestPasswordResetPage requestPasswordResetPage = landingPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "RequestPasswordReset page is not loaded.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "RequestPasswordResetSubmit page is not loaded.");

        ChooseNewPasswordPage chooseNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();


       // RequestPasswordResetSubmitPage passwordResetEmailCheckPage = passwordResetRequestSubmissionPage.followLink();
        //Assert.assertTrue(passwordResetEmailCheckPage.isPageLoaded(), "passwordResetEmailCheck page is not loaded.");

       // ChooseNewPasswordPage passwordResetRequestSubmissionPage = passwordResetEmailCheckPage.changePassword

        //PasswordChangedPage passwordChangedPage =


    }
}
