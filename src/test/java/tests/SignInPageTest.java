package tests;

import org.junit.Assert;
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
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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

//        String year = "2003";
//
//        assertThat(Integer.parseInt(year), is(lessThan(2004)));

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

        signInPage.fillYourPersonalInformation("Matei", "Costache", "123456", "Matei", "Costache", "Endava", "Vasile Parvan 202","Constanta 101", "33531","33531","", "07377711332", "Ce vreau eu" );

        signInPage.submitAccount();
        // verify if the account has been made

        assertThat(driver.getCurrentUrl(), is("http://automationpractice.com/index.php?controller=my-account"));

    }

    @Test
    public void checkRequiredFieldsErrorMessage(){
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);

        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(signInPage.submitAccount));
        DeleteElementText.deleteElementText(SignInPage.emailField);
        signInPage.fillYourPersonalInformation("", "", "", "", "", "", "","", "12345","","", "", "");
        signInPage.submitAccount();
        assertThat(signInPage.checkAllErrorsMessage(), containsString("You must register at least one phone number.\n" +
                "lastname is required.\n" +
                "firstname is required.\n" +
                "email is required.\n" +
                "passwd is required.\n" +
                "alias is required.\n" +
                "address1 is required.\n" +
                "city is required.\n" +
                "This country requires you to choose a State."));


//        assertTrue(signInPage.countryErrorMessage.isDisplayed());
//        assertTrue(signInPage.phoneNumberErrorMessage.isDisplayed());
//        assertTrue(signInPage.postalCodeErrorMessage.isDisplayed());

    }

    @Test
    public void checkInvalidInputs(){

        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);

        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(signInPage.submitAccount));

        signInPage.fillYourPersonalInformation("Matei", "Costache", "12", "Andrei", "Florin", "Endava", "Vasile Parvan 202","Constanta 101", "31","33dada531","", "07377711332", "Ce vreau eu" );
        signInPage.selectState("Alabama");
        signInPage.editEmail("262632171@23173217");
        signInPage.submitAccount();

        assertThat(signInPage.checkAllErrorsMessage(), is("email is invalid.\n" +
                "passwd is invalid.\n" +
                "The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));

    }

    @Test
    public void checkInvalidCountryInput(){

        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);

        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(signInPage.submitAccount));

        signInPage.fillYourPersonalInformation("Matei", "Costache", "123456", "Andrei", "Florin", "Endava", "Vasile Parvan 202","Constanta 101", "31222","33dada531","", "07377711332", "Ce vreau eu" );
        signInPage.selectCountry("-");
        signInPage.submitAccount();

        assertThat(signInPage.checkAllErrorsMessage(), is("id_country is required.\n" +
                "Country cannot be loaded with address->id_country\n" +
                "Country is invalid"));

    }

    @Test
    public void checkIfAgeIsEqualOrGreaterThan18 () {
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);



        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));


        signInPage.selectDayOfBirth("15");
        signInPage.selectMonthOfBirth("7");

//        String year = "2003";
//
//        assertThat(Integer.parseInt(year), is(lessThan(2004)));

        signInPage.selectYearOfBirth("1999");

    }

    @Test
    public void checkAliasToLongError(){

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

        signInPage.fillYourPersonalInformation("Matei", "Costache", "123456", "Matei", "Costache", "Endava", "Vasile Parvan 202","Constanta 101", "33531","33531","", "07377711332", "Ce vreau eushdajhkdgsajhksfaghjhfsgajkfsahfjksghajksahfjksagfhagfjkashfjaghjasgf" );

        signInPage.submitAccount();

        assertThat(signInPage.checkAllErrorsMessage(), is("alias is too long. Maximum length: 32"));

    }

    @Test
    public void checkInvisibilityOfPcodeAndState(){

        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        SignInPage signInPage = new SignInPage(driver);



        createAnAccountPage.fillCreateAnAccountField(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        signInPage.selectCountry("-");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(signInPage.stateDropdown, signInPage.postalCodeField));

        assertFalse(signInPage.stateDropdown.isDisplayed());
        assertFalse(signInPage.postalCodeField.isDisplayed());


    }



    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
