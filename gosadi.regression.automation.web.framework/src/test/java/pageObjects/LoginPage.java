package pageObjects;

import constants.FileConstant;
import logger.LOG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[text()='LOG IN']")
    private WebElement logInButton;

    @FindBy(xpath = "//div[contains(text(),'You have logged-in')]")
    private WebElement loginSuccessVerify;

    @FindBy(xpath = "//div[contains(text(),'Invalid credentials')]")
    private WebElement invalidLoginVerify;

    @FindBy(xpath = "//h1[text()='Log in to gosadi']")
    private WebElement loginPageTitle;

    @FindBy(xpath = "//button[contains(text() ,\"CREATE\" )]")
    private WebElement signupButton;

    @FindBy(xpath = "//p[text()='Please enter Email']")
    private WebElement emailErrorM;

    @FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root')]")
    private WebElement eyeIcon;

    @FindBy(xpath = "//p[contains(text(),'Remember me')]")
    private WebElement rememberMe;

    @FindBy(xpath = "//div[@role='button']/p[text()='Log out']")
    private WebElement logOutBtn;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement logOutConfirmation_Yes;

    @FindBy(xpath = "//button[text()='No']")
    private WebElement logOutConfirmation_No;

    @FindBy(xpath = "//*[text()='Forgot Password?']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//h1[text()='Forgot Password']")
    private WebElement forgotPasswordTitle;

    @FindBy(name = "email")
    private WebElement fpEmail;

    @FindBy(xpath = "//div[@class='Toastify__toast-body']")
    private WebElement emailLinkConfirmationMessage;

    @FindBy(xpath = "//p[contains(text(),'email must be ')]")
    private WebElement invalidEmailMessage;

    @FindBy(xpath = "//button[text()='Send']")
    private WebElement fpSendBtn;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement fpCancelBtn;

    @FindBy(xpath = "//*[text()='Email is required']")
    private WebElement blankEmailError;

    @FindBy(xpath = "//button[contains(text(),'CREATE YOUR')]")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//button[contains(@class,'Mui-selected') and contains(text(),'Step 1')]")
    private WebElement step1;

    @FindBy(xpath = "//button[contains(@class,'Mui-selected') and contains(text(),'Step 2')]")
    private WebElement step2;

    @FindBy(xpath = "//button[contains(@class,'Mui-selected') and contains(text(),'Step 3')]")
    private WebElement step3;

    @FindBy(xpath = "//button[contains(@class,'Mui-selected') and @value='2']")
    private WebElement monthly;

    @FindBy(xpath = "//button[contains(text(),'Yearly')]")
    private WebElement yearly;

    @FindBy(xpath = "//div[@class=' space-between']")
    private List<WebElement> planList;

    @FindBy(xpath = "//div[@class=' space-between']/p")
    private List<WebElement> priceM_Y;

    @FindBy(xpath = "(//*[contains(text(),'Start Your Free Early Access Trial')])[1]")
    private WebElement discoCP;

    @FindBy(xpath = "//*[text()='Disco']")
    private WebElement discoTitle;

    @FindBy(xpath = "(//button[contains(text(),'Start Your Free Trial')])[4]")
    private WebElement discoPlusDLP;

    @FindBy(xpath = "(//*[contains(text(),'DLP Add On')])[1]")
    private WebElement discoPlusDLPTitle;

    @FindBy(xpath = "(//button[contains(text(),'Start Your Free Early Access Trial')])[2]")
    private WebElement hustleCP;

    @FindBy(xpath = "//*[text()='Hustle']")
    private WebElement hustleTitle;

    @FindBy(xpath = "(//button[contains(text(),'Start Your Free Early Access Trial')])[5]")
    private WebElement hustlePlusDLP;

    @FindBy(xpath = "(//*[contains(text(),'DLP Add On')])[2]")
    private WebElement hustlePlusDLPTitle;

    @FindBy(xpath = "(//button[contains(text(),'Start Your Free Trial')])[3]")
    private WebElement groovyCP;

    @FindBy(xpath = "//*[text()='Groovy']")
    private WebElement groovyTitle;

    @FindBy(id = "simple-tab-0")
    private WebElement step_1;

    @FindBy(xpath = "//button[text()='Step 1']")
    private WebElement step_01;

    @FindBy(id = "simple-tab-2")
    private WebElement step_3;

    @FindBy(xpath = "//*[text()='Disco']")
    private WebElement innerPlanName;

    @FindBy(xpath = "//form[@action='#']/h1")
    private WebElement createAccountForm;

    @FindBy(xpath = "//p[contains(text(),'Special characters and ')]")
    private WebElement nameTFError;

    @FindBy(xpath = "//p[contains(text(),'Email')]")
    private WebElement emailTFError;

    @FindBy(xpath = "//p[contains(text(),'Special characters and ')]")
    private WebElement preferredNameTFError;


    @FindBy(xpath = "(//*[contains(text(),'Full Name (Required)' )])[1]")
    private WebElement nameTFErrorWithBlank;

    @FindBy(xpath = "(//*[contains(text(),'Preferred Name' )])[1]")
    private WebElement preferredNameTFErrorWithBlank;

    @FindBy(xpath = "//p[contains(text(),'valid email')]")
    private WebElement invalidEmailTFError;

    @FindBy(xpath = "//div[contains(text(),'Email already exists')]")
    private WebElement alreadyRegisteredEmail;

    @FindBy(xpath = "//button[text()='Agree']")
    private WebElement betaTesterConfiAgree;


    @FindBy(xpath = "//p[contains(text(),'valid phone number')]")
    private WebElement phoneNumberError;

    @FindBy(xpath = "(//*[contains(text(),'Phone (Required)')])[1]")
    private WebElement phoneNumberMandatory;

    @FindBy(xpath = "//p[contains(text(),'Postal code')]")
    private WebElement postalCodeMandatory;

    @FindBy(xpath = "//p[contains(text(),'Password must be')]")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "(//*[contains(text(),'Password (Required)' )])[1]")
    private WebElement mandatoryPassword;

    @FindBy(xpath = "//button[contains(text(),'Sign up now to join the gosadi Early Access')]")
    private WebElement caFormSubmit;

    @FindBy(name = "fullName")
    private WebElement fullNameTF;

    @FindBy(name = "email")
    private WebElement emailTF;

    @FindBy(name = "phone")
    private WebElement phoneNoTF;

    @FindBy(name = "preferredName")
    private WebElement preferredNameTF;

    @FindBy(name = "phoneCountry")
    private WebElement countryCodeDD;

    @FindBy(xpath = "//*[@id=\":r7:\"]")
    private WebElement country;

    @FindBy(name = "postalCode")
    private WebElement postalCode;

    @FindBy(name = "password")
    private WebElement caPassword;

    @FindBy(xpath = "(//button[@aria-label=\"show or hide password\"])[1]")
    private WebElement caPasswordEye;

    @FindBy(xpath = "//input[@type=\"password\" and @name='password']")
    private WebElement caPasswordEyeHidden;

    @FindBy(xpath = "//input[@type=\"text\" and @name='password']")
    private WebElement caPasswordEyeUnHidden;

    @FindBy(xpath = "//input[@name='isNewsLetterSubscribed']")
    private WebElement newsLetterSubscribedCB;

    @FindBy(xpath = "//p[contains(text(),'Sign up for gosadi updates')]")
    private WebElement newsLetterSubscribedCBIsChecked;

    @FindBy(xpath = "//a[contains(text(),'terms of service')]")
    private WebElement termOfService;

    @FindBy(xpath = "//h1[text()='Terms & Conditions']")
    private WebElement termOfServiceTittle;

    @FindBy(xpath = "//a[contains(text(),'privacy policy')]")
    private WebElement privacyPolicy;

    @FindBy(xpath = "//h1[text()='Privacy Policy']")
    private WebElement privacyPolicyTittle;

    @FindBy(xpath = "//a[text()='Log In']")
    private WebElement loginLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage verifyURL() {
        String actual_Title = driver.getTitle();
        String expected_Title = "gosadi - web";
        LOG.Reporter("Title of the Page is : " + actual_Title);
        Assert.assertEquals(actual_Title, expected_Title);
        LOG.Reporter("URL of the application verified ");
        return this;
    }

    public void validateEmailAndPassword() {
        sendKeys(email, FileConstant.getEnvironmentKey(("user_1")));
        LOG.Reporter("Entered Email address : " + getAttributeValue(email));
        sendKeys(password, FileConstant.getEnvironmentKey(("userPass_1")));
        LOG.Reporter("Entered Password ");
    }

    public LoginPage verifyAllTextLabelsAndTF() {
        Assert.assertTrue(iselementVisible(loginPageTitle, second10TimeOut), "Labels not displayed");
        String h1_labels = loginPageTitle.getText();
        String h1_labelsExpected = "Log in to gosadi";
        Assert.assertEquals(h1_labels, h1_labelsExpected);
        LOG.Reporter("H1 label is Verified : " + h1_labels);
        Assert.assertTrue(iselementVisible(email, second10TimeOut), "Email Text filed not visible");
        LOG.Reporter("Email text filed is display");
        Assert.assertTrue(iselementVisible(password, second10TimeOut), "Password Text filed not visible");
        LOG.Reporter("Password text filed is display");
        Assert.assertTrue(iselementVisible(logInButton, second10TimeOut), "Login Button is not visible");
        LOG.Reporter("Login Button is display ");
        Assert.assertTrue(iselementVisible(signupButton, second10TimeOut), "Signup Button is not visible");
        LOG.Reporter("Create your account Button is display ");
        return this;
    }

    public LoginPage verifyUserLogin() {
        validateEmailAndPassword();
        iselementClickable(logInButton, second10TimeOut);
        clickOnWebElement(logInButton);
        LOG.Reporter("Click on Login button");
        waitForElement(driver, loginSuccessVerify, Duration.ofSeconds(5));
        Assert.assertTrue(iselementVisible(loginSuccessVerify, second10TimeOut), "Login Successful message not visible");
        LOG.Reporter("Seller Login Successfully");
        return this;
    }

    public LoginPage verifyWithInvalidPassword() {
        sendKeys(email, FileConstant.getEnvironmentKey(("user_1")));
        LOG.Reporter("Entered Email address : " + getAttributeValue(email));
        sendKeys(password, FileConstant.getEnvironmentKey(("invalidPass")));
        LOG.Reporter("Entered Password : " + getAttributeValue(password));
        iselementClickable(logInButton, second10TimeOut);
        clickOnWebElement(logInButton);
        LOG.Reporter("Click on Login button");
        waitForElement(driver, invalidLoginVerify, Duration.ofSeconds(5));
        Assert.assertTrue(iselementVisible(invalidLoginVerify, second10TimeOut), "Invalid credentials message not visible");
        LOG.Reporter("Invalid credentials message display");
        return this;
    }

    public LoginPage verifyWithInvalidEmail() {
        sendKeys(email, FileConstant.getEnvironmentKey("invalidEmail"));
        LOG.Reporter("Entered Email address : " + getAttributeValue(email));
        sendKeys(password, FileConstant.getEnvironmentKey("userPass_1"));
        LOG.Reporter("Entered Password ");
        iselementClickable(logInButton, second10TimeOut);
        clickOnWebElement(logInButton);
        LOG.Reporter("Click on Login button");
        waitForVisibility(invalidLoginVerify);
        waitForElement(driver, invalidLoginVerify, Duration.ofSeconds(5));
        Assert.assertTrue(iselementVisible(invalidLoginVerify, second10TimeOut), "Invalid credentials message not visible");
        LOG.Reporter("Invalid credentials message display");
        return this;
    }


}
