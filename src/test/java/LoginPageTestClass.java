import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginPageTestClass{
    private WebDriver driver = new ChromeDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    @Before
    public void setup(){
        driver.get("https://preprod-smartbox.cs86.force.com/s/login/?language=en_GB");
        loginPage.tryToWaitForElement(ExpectedConditions.elementToBeClickable(loginPage.loginButton),
                "\n**Fail on setup** \nLogin button was not found");
    }

    @Test
    public void t1_loginWithWrongUsername(){
        loginPage.setUsername("wrong.username@test.com.preprod.community");
        loginPage.setPassword(System.getenv("SB_PREPROD_COMMUNITY_PASSWORD"));
        loginPage.clickIAcceptTermsAndConditionsCheckbox();
        loginPage.clickLoginButton();
        loginPage.tryToWaitForCondition(ExpectedConditions.textToBePresentInElement(loginPage.incorrectUsernameOrPasswordMessage,
                "Your login attempt has failed. Make sure the username and password are correct."),
                "\n**Fail on t1_loginWithWrongUsername** \nLogin error message is wrong (or it`s not displayed)");
        Assert.assertTrue("Incorrect login error message is not displayed",
                loginPage.incorrectUsernameOrPasswordMessage.isDisplayed());
    }

    @Test
    public void t2_loginWithWrongPassword(){
        loginPage.setUsername(System.getenv("SB_PREPROD_COMMUNITY_USERNAME"));
        loginPage.setPassword("Wrong_Password");
        loginPage.clickIAcceptTermsAndConditionsCheckbox();
        loginPage.clickLoginButton();
        loginPage.tryToWaitForCondition(ExpectedConditions.textToBePresentInElement(loginPage.incorrectUsernameOrPasswordMessage,
                "Your login attempt has failed. Make sure the username and password are correct."),
                "\n**Fail on t2_loginWithWrongPassword** \nLogin error message is wrong (or it`s not displayed)");
        Assert.assertTrue("Incorrect password error message is not displayed or this message is wrong",
                loginPage.incorrectUsernameOrPasswordMessage.isDisplayed());
    }

    @Test
    public void t3_loginWithoutAcceptingTermsAndConditions(){
        loginPage.setUsername(System.getenv("SB_PREPROD_COMMUNITY_USERNAME"));
        loginPage.setPassword(System.getenv("SB_PREPROD_COMMUNITY_PASSWORD"));
        loginPage.clickLoginButton();
        loginPage.tryToWaitForCondition(ExpectedConditions.textToBePresentInElement(loginPage.indicateAgreementWithTermsAndConditionsErrorMessage,
                "Please indicate that you agree to the Terms And Conditions"),
                "\n**Fail on t3_loginWithoutAcceptingTermsAndConditions** \nTerms and Conditions message is wrong (or it`s not displayed)");
        Assert.assertTrue("Indicate agreement with Terms and Conditions message is not displayed",
                loginPage.indicateAgreementWithTermsAndConditionsErrorMessage.isDisplayed());
    }

    @Test
    public void t4_loginToSalesforce(){
        loginPage.setUsername(System.getenv("SB_PREPROD_COMMUNITY_USERNAME"));
        loginPage.setPassword(System.getenv("SB_PREPROD_COMMUNITY_PASSWORD"));
        loginPage.clickIAcceptTermsAndConditionsCheckbox();
        loginPage.clickLoginButton();
        loginPage.tryToWaitForElement(ExpectedConditions.presenceOfElementLocated(By.className("navigationMenuWrapper")),
                "\n**Fail on t4_loginToSalesforce** \nNavigation menu bar was not found");
        Assert.assertTrue("Partner should be redirected to Home page after log in", driver.getCurrentUrl().equals("https://preprod-smartbox.cs86.force.com/s/"));
    }

    @Test
    public void t5_openTermsAndConditionsModalScreen(){
        loginPage.clickTermsAndConditionsLink();
        Assert.assertTrue("Terms and Conditions modal screen does not displayed after clicking 'Terms and Conditions' link",
                loginPage.termsAndConditionsModalScreen.isDisplayed());
    }

    @Test
    public void t6_acceptButtonIsDisabledAfterOpeningTermsAndConditionsModalScreen(){
        loginPage.clickTermsAndConditionsLink();
        loginPage.tryToWaitForElement(ExpectedConditions.visibilityOf(loginPage.termsAndConditionsModalScreen),
                "\n**Fail on t6_acceptButtonIsDisabledAfterOpeningTermsAndConditionsModalScreen** \nTerms and Conditions modal window was not opened");
        Assert.assertTrue("'Accept' button should not be clickable until Terms and Conditions scrolled down",
                !loginPage.termsAndConditionsAcceptButtonIsClickable());
    }

    @Test
    public void t7_acceptButtonIsActiveAfterScrollingDownTermsAndConditionsModalScreen(){
        loginPage.clickTermsAndConditionsLink();
        loginPage.tryToWaitForElement(ExpectedConditions.visibilityOf(loginPage.termsAndConditionsModalScreen),
                "\n**Fail on t7_acceptButtonIsActiveAfterScrollingDownTermsAndConditionsModalScreen** \nTerms and Conditions modal window was not opened");
        jse.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", driver.findElements(By.className("slds-modal__content")).get(1));
        Assert.assertTrue("'Accept' button should be clickable after Terms and Conditions scrolled down",
                loginPage.termsAndConditionsAcceptButtonIsClickable());
    }

    @Test
    public void t8_checkAboutMyUsernameTextIsDisplayedAfterClickDownButton(){
        loginPage.clickAboutMyUsernameArrow();
        Assert.assertTrue("'About my username' text is not displayed (or it`s wrong)", loginPage.aboutMyUsernameTextIsDisplayed());
    }

    @Test
    public void t9_checkForgotYourPasswordRedirectLink(){
        loginPage.clickForgotYourPasswordLink();
        loginPage.tryToWaitForElement(ExpectedConditions.elementToBeClickable(By.cssSelector("button.sfdc_button.uiButton--default.uiButton")),
                "Reset password page was not downloaded");
        Assert.assertTrue("Resset password page was not downloaded or it has wrong URL",
                driver.getCurrentUrl().equals("https://preprod-smartbox.cs86.force.com/s/login/ForgotPassword?language=en_GB"));
    }

    @After
    public void teardown(){
        if(driver != null)
        driver.quit();
    }
}
