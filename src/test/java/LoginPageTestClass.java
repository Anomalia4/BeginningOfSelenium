import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginPageTestClass {
    private WebDriver driver = new ChromeDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private WebDriverWait wait = new WebDriverWait(driver, 5, 1000);

    @Before
    public void setup(){
        driver.get("https://preprod-smartbox.cs86.force.com/s/login/?language=en_GB");
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));
    }

    @Test
    public void t1_loginWithWrongUsername(){
        loginPage.setUsername("wrong.username@test.com.preprod.community");
        loginPage.setPassword(System.getenv("SB_PREPROD_COMMUNITY_PASSWORD"));
        loginPage.clickIAcceptTermsAndConditions();
        loginPage.clickLoginButton();
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.incorrectUsernameOrPasswordMessage,
                "Your login attempt has failed. Make sure the username and password are correct."));
        Assert.assertTrue("Incorrect login error message is not displayed or this message is wrong",
                loginPage.incorrectUsernameOrPasswordMessage.isDisplayed());
    }

    @Test
    public void t2_loginWithWrongPassword(){
        loginPage.setUsername(System.getenv("SB_PREPROD_COMMUNITY_USERNAME"));
        loginPage.setPassword("Wrong_Password");
        loginPage.clickIAcceptTermsAndConditions();
        loginPage.clickLoginButton();
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.incorrectUsernameOrPasswordMessage,
                "Your login attempt has failed. Make sure the username and password are correct."));
        Assert.assertTrue("Incorrect password error message is not displayed or this message is wrong",
                loginPage.incorrectUsernameOrPasswordMessage.isDisplayed());
    }

    @Test
    public void t3_loginWithoutAcceptingTermsAndCondtions(){
        loginPage.setUsername(System.getenv("SB_PREPROD_COMMUNITY_USERNAME"));
        loginPage.setPassword(System.getenv("SB_PREPROD_COMMUNITY_PASSWORD"));
        loginPage.clickLoginButton();
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.indicateAgreementWithTermsAndConditionsErrorMessage,
                "Please indicate that you agree to the Terms And Condtitions"));
        Assert.assertTrue("Indicate agreement with Terms and Conditions message is not displayed or this message is wrong", loginPage.indicateAgreementWithTermsAndConditionsErrorMessage.isDisplayed());
    }


    @Test
    public void t4_loginToSalesforce(){
        loginPage.setUsername(System.getenv("SB_PREPROD_COMMUNITY_USERNAME"));
        loginPage.setPassword(System.getenv("SB_PREPROD_COMMUNITY_PASSWORD"));
        loginPage.clickIAcceptTermsAndConditions();
        loginPage.clickLoginButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("navigationMenuWrapper")));
        Assert.assertTrue("Partner should be redirected to Home page after log in", driver.getCurrentUrl().contains("smartbox.cs86.force.com/s/"));
    }

    @After
    public void teardown(){
        if(driver != null)
        driver.quit();
    }
}
