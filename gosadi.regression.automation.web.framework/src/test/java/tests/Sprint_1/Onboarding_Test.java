package tests.Sprint_1;


import io.qameta.allure.*;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import tests.SuiteSetup;
import utilities.JiraPolicy;

import static io.qameta.allure.SeverityLevel.*;

@Epic("Onboarding")
public class Onboarding_Test extends SuiteSetup {

    public LoginPage page;


    @JiraPolicy(logTicketReady = false)
    @Test(priority = 1, description = "GP_Web_001")
    @Feature("Login")
    @Severity(CRITICAL)
    @Description("Verify URL https://app.gosadi.com/login redirects to the the source page or not ")
    @Story("As a user I should be able to open the URL")
    public void testToVerifyURL() {
        page = new LoginPage(getDriver());
        page.verifyURL();
    }

    @JiraPolicy(logTicketReady = false)
    @Test(priority = 2, description = "GP_Web_002")
    @Feature("SignUP")
    @Severity(BLOCKER)
    @Description("Verify that all the labels and controls including text-boxes, buttons, and links are present on the Login page.")
    @Story("As a user I should be able to verify all the labels,text-boxes and buttons")
    public void testToVerifyAllTextLabelsAndTF() {
        page = new LoginPage(getDriver());
        page.verifyAllTextLabelsAndTF();
    }


    @JiraPolicy(logTicketReady = false)
    @Test(priority = 3, description = "GP_Web_003")
    @Feature("SignUP")
    @Severity(MINOR)
    @Description("Verify if a user will be able to login with a valid username and valid password.")
    @Story("As a user I should be able to Login to the application")
    public void testToVerifySellerLogin() {
        page = new LoginPage(getDriver());
        page.verifyUserLogin();
    }
    @JiraPolicy(logTicketReady = false)
    @Test(priority = 4, description = "GP_Web_004")
    @Feature("Login")
    @Severity(MINOR)
    @Description("Verify if a user cannot login with a valid username and an invalid password.")
    @Story("As a user I should not be able to Login to the application")
    public void testToVerifyInvalidSellerLogin() {
        page = new LoginPage(getDriver());
        page.verifyWithInvalidPassword();
    }

    @JiraPolicy(logTicketReady = false)
    @Test(priority = 5, description = "GP_Web_005")
    @Feature("Login")
    @Severity(MINOR)
    @Description("Verify if a user cannot login with a invalid email and an valid password.")
    @Story("As a user I should not be able to Login with invalid email an valid password")
    public void testToVerifyInvalidEmail() {
        page = new LoginPage(getDriver());
        page.verifyWithInvalidEmail();
    }


}