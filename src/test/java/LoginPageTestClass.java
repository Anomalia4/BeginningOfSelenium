import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageTestClass {
    private WebDriver driver = new ChromeDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private WebDriverWait wait = new WebDriverWait(driver, 4, 800);

    @Before
    public void setup(){
        driver.get("https://preprod-smartbox.cs86.force.com/s/login/?language=en_GB");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("71:2;a")));
    }

    @Test
    public void loginWithWrongUsername(){
        loginPage.setUsername("wrong.username@test.com.preprod.community");
        loginPage.setPassword("tHXBqJgrmSSbhItN3cRF");
        loginPage.clickIAcceptTermsAndConditions();
        loginPage.clickLoginButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.incorrectUsernameOrPasswordMessage));
        Assert.assertTrue("Incorrect login error message is not displayed", loginPage.incorrectUsernameOrPasswordMessageIsDisplayed());
    }

    @Test
    public void loginWithWrongPassword(){
        loginPage.setUsername("sergey.karpenko@pexlify.com.preprod.community");
        loginPage.setPassword("Wrong_Password");
        loginPage.clickIAcceptTermsAndConditions();
        loginPage.clickLoginButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.incorrectUsernameOrPasswordMessage));
        Assert.assertTrue("Incorrect password error message is not displayed", loginPage.incorrectUsernameOrPasswordMessageIsDisplayed());
    }


    @Test
    public void loginToSalesforce(){
        loginPage.setUsername("sergey.karpenko@pexlify.com.preprod.community");
        loginPage.setPassword("tHXBqJgrmSSbhItN3cRF");
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
