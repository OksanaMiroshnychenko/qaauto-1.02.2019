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

        PasswordResetRequestSubmissionPage passwordResetRequestSubmissionPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(passwordResetRequestSubmissionPage.isPageLoaded(), "PasswordResetRequestSubmission page is not loaded.");

        PasswordResetEmailCheckPage passwordResetEmailCheckPage =


        //PasswordChangedPage passwordChangedPage =

        //изменение пароля, проверка что страница загр.
        //переход на домашнюю страницу

    }
}
