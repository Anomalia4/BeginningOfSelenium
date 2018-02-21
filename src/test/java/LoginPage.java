import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    private WebDriver driver;
   // private WebDriverWait wait;
    public LoginPage(WebDriver webDriver){
        driver = webDriver;
       // wait = new WebDriverWait(driver, 3, 500);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "pmp_login_username")
    private WebElement usernameField;

    @FindBy(className = "pmp_login_password")
    private WebElement passwordField;

    @FindBy(className = "rememberCheckbox")
    private WebElement remeberMeCheckbox;
    
    @FindBy(xpath = "//input[@id='120:2;a' and @class='terms_and_conditions_checkbox uiInput uiInputCheckbox uiInput--default uiInput--checkbox']")
    private WebElement iAcceptTermsAndConditionsCheckbox;

    @FindBy(linkText = "Terms and Conditions")
    private WebElement termsAndConditionsLink;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotYourPasswordLink;

    @FindBy(className = "pmp_login_button")
    WebElement loginButton;

    @FindBy(xpath = "//h4[contains(text(), 'Your login attempt has failed. Make sure the username and password are correct.')]")
    protected WebElement incorrectUsernameOrPasswordMessage;

    public void setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickRememberMe(){
        remeberMeCheckbox.click();
    }

    public void clickIAcceptTermsAndConditions(){
        iAcceptTermsAndConditionsCheckbox.click();
    }

    public boolean incorrectUsernameOrPasswordMessageIsDisplayed(){
        return (incorrectUsernameOrPasswordMessage.isDisplayed());
    }
}
