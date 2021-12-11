package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAnAccountPage;
import pages.SignInPage;
import util.DeleteElementText;
import util.RandomEmail;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SignInPageTest {

    WebDriver driver;

    @BeforeEach
    public void setUp () {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dapadurean\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

    }

    @Test
    public void checkSuccesfullCreateAnAccount () {
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);


        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));
        signInPage.selectGender("mr.");
        signInPage.selectState("Alabama");
        signInPage.selectDayOfBirth("15");
        signInPage.selectMonthOfBirth("7");
        signInPage.selectYearOfBirth("1999");
        signInPage.selectNewsletterCheckbox();
        signInPage.selectOffersCheckbox();
        signInPage.fillYourPersonalInformation("Matei", "Costache", "123456", "Matei", "Costache", "Endava", "Vasile Parvan 202","Constanta 101", "Timisoara","33531"," Bla bla bla", "07377711332", "", "Ce vreau eu" );

    }

    @Test
    public void checkRequiredFieldsErrorMessage(){
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);


        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));
        DeleteElementText.deleteElementText(SignInPage.emailField);
        signInPage.fillYourPersonalInformation("", "", "", "", "", "", "","", "","","", "", "", "" );

        signInPage.checkAtLeastOnePhoneNumberErrorMessage();
        signInPage.checkLastNameRequiredMessage();
        signInPage.checkFirstNameRequiredMessage();
        signInPage.checkEmailRequiredMessage();
        signInPage.checkPasswordRequiredMessage();
        signInPage.checkAliasRequiredMessage();
        signInPage.checkAddressRequiredMessage();
        signInPage.checkCityRequiredMessage();
        signInPage.checkCountryInvalidMessage();
        signInPage.checkCountryErrorMessage();




    }

//    @AfterEach
//    public void tearDown(){
//        driver.quit();
//    }

}
