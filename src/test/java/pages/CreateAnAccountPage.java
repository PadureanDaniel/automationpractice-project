package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateAnAccountPage {

    @FindBy(id = "create_account_error")
    private WebElement invalidEmailErrorMessage;

    @FindBy(id = "email_create")
    private WebElement inputEmailAddress;

    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountButton;

    @FindBy(xpath = "//li[contains(text(), \"An account using this email\")]")
    private WebElement usedEmailErrorMessage;

    protected WebDriver driver;

    public CreateAnAccountPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void fillCreateAnAccountField (String email) {

        inputEmailAddress.sendKeys(email);
        createAnAccountButton.click();

    }

    public String getInvalidEmailErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(invalidEmailErrorMessage));
        String errorMessage = invalidEmailErrorMessage.getText();
        return errorMessage;


    }

    // not necessary, we could use only one method for used email and invalid email
    public String getUsedEmailErrorMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(usedEmailErrorMessage));
        String usedEmail = usedEmailErrorMessage.getText();
        return usedEmail;


    }

    public void deleteEmail(){

        inputEmailAddress.clear();

    }

}
