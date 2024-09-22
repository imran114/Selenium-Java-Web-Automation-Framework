package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test_base.PageObjects;

public class LoginTest extends PageObjects {

    @Test(priority = 1)
    public void enterEmailAddress() {
        useCaseName = "Use case name";
        emailBody.enterTextToEmailBody("- " + useCaseName);
        parentTest(useCaseName, "Test cases of "+useCaseName);
        extentReport.createTest(useCaseName, "enter email address");
        extentReport.logChildTestNameAndDescription("Enter Email", "Enter email address");
        actualResult = loginPage.enterEmail("test email");
        extentReport.testInfo("verify if email is entered");
        extentReport.logStepResult(actualResult);
        Assert.assertTrue(actualResult.contains("Pass"));
    }

    @Test(priority = 2)
    public void enterPassword() {
        extentReport.logChildTestNameAndDescription("Enter Password", "Enter Password in the password field");
        actualResult = loginPage.enterPassword("test password");
        extentReport.testInfo("Verify if Password is entered Successfully");
        extentReport.logStepResult(actualResult);
        Assert.assertTrue(actualResult.contains("Pass"));

    }

    @Test(priority = 3)
    public void clickSignInButton() {
        actualResult = loginPage.clickSignInButton();
        extentReport.logChildTestNameAndDescription("Click Sign in Button", "Click Sign in Button to sign in");
        extentReport.testInfo("Verify if Sign in Button is Clicked");
        extentReport.logStepResult(actualResult);
        Assert.assertTrue(actualResult.contains("Pass"));

    }


}
