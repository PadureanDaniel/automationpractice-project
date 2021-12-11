package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAnAccountPage;
import util.DeleteElementText;
import util.RandomEmail;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class CreateAnAccountPageTest {

    WebDriver driver;

    @BeforeEach
    public void setUp () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dapadurean\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }


    @Test
    public void checkCreateAnAccount (){
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();


        // empty field

        createAnAccountPage.fillCreateAnAccountField("");
        assertThat(createAnAccountPage.getInvalidEmailErrorMessage(), containsString("Invalid email address."));


        // invalid email

        createAnAccountPage.fillCreateAnAccountField("miaumiau@miau");
        assertThat(createAnAccountPage.getInvalidEmailErrorMessage(), containsString("Invalid email address."));
        DeleteElementText.deleteElementText(CreateAnAccountPage.inputEmailAddress);

        // used email

        createAnAccountPage.fillCreateAnAccountField("bauhau@mail.com");
        assertThat(createAnAccountPage.getUsedEmailErrorMessage(), containsString("An account using this email address has already been"));
        DeleteElementText.deleteElementText(CreateAnAccountPage.inputEmailAddress);


        // valid email

        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));
        assertThat(driver.getCurrentUrl(), containsString("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));



    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
