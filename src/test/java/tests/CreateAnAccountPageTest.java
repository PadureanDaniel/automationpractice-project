package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.RandomEmail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        pages.CreateAnAccountPage createAnAccountPage = new pages.CreateAnAccountPage(driver);
        String email = RandomEmail.generateRandomEmail();

        // empty field

        createAnAccountPage.fillCreateAnAccountField("");
        assertThat(createAnAccountPage.getInvalidEmailErrorMessage(), containsString("Invalid email address."));


        // invalid email

        createAnAccountPage.fillCreateAnAccountField("miaumiau@miau");
        assertThat(createAnAccountPage.getInvalidEmailErrorMessage(), containsString("Invalid email address."));
        createAnAccountPage.deleteEmail();

        // used email

        createAnAccountPage.fillCreateAnAccountField("bauhau@mail.com");
        assertThat(createAnAccountPage.getUsedEmailErrorMessage(), containsString("An account using this email address has already been"));
        createAnAccountPage.deleteEmail();


        // valid email

        createAnAccountPage.fillCreateAnAccountField(email);



    }


//    @AfterEach
//    public void tearDown(){
//        driver.quit();
//    }

}
