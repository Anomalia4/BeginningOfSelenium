import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public LoginPage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver, 5, 800);
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
    protected WebElement loginButton;

    @FindBy(css = "div.slds-modal.slds-fade-in-open.cModal.cTermsAndConditionsModal")
    protected WebElement termsAndConditionsModalScreen;

    @FindBy(xpath = "//h4[contains(text(), 'Your login attempt has failed. Make sure the username and password are correct.')]")
    protected WebElement incorrectUsernameOrPasswordMessage;

    @FindBy(xpath = "//h4[contains(text(), 'Please indicate that you agree to the Terms And Condtitions')]")
    protected WebElement indicateAgreementWithTermsAndConditionsErrorMessage;

    @FindBy(css = "button.slds-button.slds-p-horizontal_large")
    private WebElement termsAndConditionsAcceptButton;

    @FindBy(className = "iconDown")
    private WebElement aboutMyUsernameArrow;

    @FindBy(className = "slds-p-top--small")
    private WebElement aboutMyUsernameText;

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

    public void clickIAcceptTermsAndConditionsCheckbox(){
        iAcceptTermsAndConditionsCheckbox.click();
    }

    public void clickTermsAndConditionsLink(){
        termsAndConditionsLink.click();
    }

    public boolean termsAndConditionsAcceptButtonIsClickable(){
        return termsAndConditionsAcceptButton.isEnabled();
    }

    public void clickAboutMyUsernameArrow(){
        aboutMyUsernameArrow.click();
    }

    public boolean aboutMyUsernameTextIsDisplayed(){
        return aboutMyUsernameText.isDisplayed();
    }

    public void clickForgotYourPasswordLink(){
        forgotYourPasswordLink.click();
    }

    public void tryToWaitForElement(ExpectedCondition<WebElement> conditions, String errorMessage){
        try {
            wait.until(conditions);
        }
        catch (Exception ex){
            System.out.println(errorMessage);
        }
    }

    public void tryToWaitForCondition(ExpectedCondition<Boolean> conditions, String errorMessage){
        try {
            wait.until(conditions);
        }
        catch (Exception ex){
            System.out.println(errorMessage);
        }
    }
}
