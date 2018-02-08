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

    @FindBy(id = "71:2;a")
    private WebElement usernameField;

    @FindBy(id = "81:2;a")
    private WebElement passwordField;

    @FindBy(id = "93:2;a")
    private WebElement remeberMeCheckbox;

    @FindBy(xpath = "//input[@id='103:2;a' and @class='uiInput uiInputCheckbox uiInput--default uiInput--checkbox']")
    private WebElement iAcceptTermsAndConditionsCheckbox;

    @FindBy(linkText = "Terms and Conditions")
    private WebElement termsAndConditionsLink;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotYourPasswordLink;

    @FindBy(css = "button.slds-button.slds-button--brand.pmp_login_button.uiButton--default.uiButton")
    WebElement loginButton;

    @FindBy(xpath = "//h4[contains(text(), 'Your login attempt has failed. Make sure the username and password are correct.')]")
    WebElement incorrectUsernameOrPasswordMessage;

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
