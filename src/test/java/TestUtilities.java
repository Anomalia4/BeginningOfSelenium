import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class TestUtilities {
    private WebDriverWait wait;
    public TestUtilities(WebDriver driver){
        wait = new WebDriverWait(driver, 5, 800);
    }
    public void tryToWaitForElement(ExpectedCondition<WebElement> conditions, String errorMessage){
        try {
            wait.until(conditions);
        }
        catch (Exception ex){
            Assert.assertNull(errorMessage);
        }
    }

    public void tryToWaitForCondition(ExpectedCondition<Boolean> conditions, String errorMessage){
        try {
            wait.until(conditions);
        }
        catch (Exception ex){
            Assert.assertNull(errorMessage);
        }
    }

    public int getRandomIntegerValue(int maxBound){
        Random random = new Random();
        return random.nextInt(maxBound);
    }
}
