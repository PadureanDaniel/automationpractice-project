package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DeleteElementText;

import java.lang.reflect.UndeclaredThrowableException;
import java.nio.channels.WritableByteChannel;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class SignInPage {

    // Your Personal Information

    @FindBy(className = "radio-inline")
    private List<WebElement> selectGender;

    @FindBy(id = "customer_firstname")
    private WebElement firstName;
    @FindBy(id = "customer_lastname")
    private WebElement lastName;
    @FindBy(id = "email")
    public static WebElement emailField;
    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement dateOfBirthDay;
    @FindBy(id = "months")
    private WebElement dateOfBirthMonth;
    @FindBy(id = "years")
    private WebElement dateOfbirthYear;
    @FindBy(id = "newsletter")
    private WebElement newsletter;
    @FindBy(id = "optin")
    private WebElement specialOffers;


    // Your Address

    @FindBy(id = "firstname")
    private WebElement firstName2;
    @FindBy(id = "lastname")
    private WebElement lastName2;

    @FindBy(id = "company")
    private WebElement companyField;
    @FindBy(id = "address1")
    private WebElement addressField;
    @FindBy(id = "address2")
    private WebElement addressField2;

    @FindBy(id = "city")
    private WebElement cityField;
    @FindBy(id = "id_state")
    public WebElement stateDropdown;
    @FindBy(id = "postcode")
    public WebElement postalCodeField;
    @FindBy(id = "id_country")
    private WebElement countryDropdown;
    @FindBy(id = "other")
    private WebElement additionalInfoField;
    @FindBy(id = "phone")
    private WebElement homePhoneField;
    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneField;
    @FindBy(id = "alias")
    private WebElement addressAliasField;
    @FindBy(id = "submitAccount")
    public WebElement submitAccount;

    // Error Messages

    @FindBy(xpath = "//li[contains(text(), \"You must register at least one phone number.\")]")
    public WebElement phoneNumberErrorMessage;
    @FindBy(xpath = "//li[contains(text(),\"This country requires you to choose a State.\")]")
    public WebElement countryErrorMessage;
    @FindBy(xpath = "//li[contains(text(),\"The Zip/Postal code you've entered is invalid. It \")]")
    public WebElement postalCodeErrorMessage;
//    @FindBy(xpath = "//b[contains(text(), \"lastname\")]")
//    private WebElement lastNameErrorMessage;
//
//    @FindBy(xpath = "//b[contains(text(), \"firstname\")]")
//    private WebElement firstNameErrorMessage;





    protected WebDriver driver;
    public SignInPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void fillYourPersonalInformation(String firstname, String lastname, String password, String firstNameAddres, String lastNameAddress, String company, String address, String city, String postalCode, String additionalInfo, String homePhone, String mobilePhone, String alias) {

        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        passwordField.sendKeys(password);
        if(firstName2.getText() != firstNameAddres) {
            firstName2.clear();
            firstName2.sendKeys(firstNameAddres);
        }
        if(lastName2.getText() != lastNameAddress) {
            lastName2.clear();
            lastName2.sendKeys(lastNameAddress);
        }
        companyField.sendKeys(company);
        addressField.sendKeys(address);
        //addressField2.sendKeys(address2);
        cityField.sendKeys(city);
        postalCodeField.sendKeys(postalCode);
        additionalInfoField.sendKeys(additionalInfo);
        homePhoneField.sendKeys(homePhone);
        mobilePhoneField.sendKeys(mobilePhone);
        DeleteElementText.deleteElementText(addressAliasField);
        addressAliasField.sendKeys(alias);


    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("mr."))
            selectGender.get(0).click();
        else if (gender.equalsIgnoreCase("mrs."))
            selectGender.get(1).click();
    }


    public void selectDayOfBirth(String day){

        Select dobDropDown = new Select(dateOfBirthDay);
        dobDropDown.selectByValue(day);

    }
    public void selectMonthOfBirth(String month){

        Select dobDropDown = new Select(dateOfBirthMonth);
        dobDropDown.selectByValue(month);

    }
    public void selectYearOfBirth(String year){

        Select dobDropDown = new Select(dateOfbirthYear);

            if (Integer.parseInt(year) <= 2003) {

                dobDropDown.selectByValue(year);

            } else {
                assertThat(Integer.parseInt(year), is(lessThan(2004)));
            }



    }


    public void selectNewsletterCheckbox(){
        if(!newsletter.isSelected())
            newsletter.click();
    }

    public void selectOffersCheckbox(){
        if(!specialOffers.isSelected())
            specialOffers.click();
    }

    public void selectState(String state){

        Select selectStateDOB = new Select(stateDropdown);
        selectStateDOB.selectByVisibleText(state);

    }

    public void selectCountry(String country){

        Select selectCountryDOB = new Select(countryDropdown);
        selectCountryDOB.selectByVisibleText(country);

    }

    public void editEmail(String email){

        DeleteElementText.deleteElementText(emailField);
        emailField.sendKeys(email);

    }

    public String checkAllErrorsMessage(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id = 'center_column']/div"))));
        WebElement parentElement = driver.findElement(By.xpath("//*[@id = 'center_column']/div/ol"));
        String allText = parentElement.getText();
        System.out.println(allText);
        return allText;

    }

    public void submitAccount(){

        submitAccount.click();

    }


}
