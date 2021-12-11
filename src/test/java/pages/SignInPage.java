package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.DeleteElementText;

import java.util.List;

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
    private WebElement stateDropdown;
    @FindBy(id = "postcode")
    private WebElement postalCodeField;
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
    private WebElement submitAccount;

    protected WebDriver driver;
    public SignInPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void fillYourPersonalInformation(String firstname, String lastname, String password, String firstNameAddres, String lastNameAddress, String company, String address, String address2, String city, String postalCode, String additionalInfo, String homePhone, String mobilePhone, String alias) {

        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        passwordField.sendKeys(password);
        if(firstName2.getText() == firstNameAddres)
            firstName2.sendKeys(firstNameAddres);
        if(lastName2.getText() == lastNameAddress)
            lastName2.sendKeys(lastNameAddress);
        companyField.sendKeys(company);
        addressField.sendKeys(address);
        addressField2.sendKeys(address2);
        cityField.sendKeys(city);
        postalCodeField.sendKeys(postalCode);
        additionalInfoField.sendKeys(additionalInfo);
        homePhoneField.sendKeys(homePhone);
        mobilePhoneField.sendKeys(mobilePhone);
        DeleteElementText.deleteElementText(addressAliasField);
        addressAliasField.sendKeys(alias);

        submitAccount.click();


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
        dobDropDown.selectByValue(year);

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


    public String checkAtLeastOnePhoneNumberErrorMessage() {
    }

    public String checkLastNameRequiredMessage() {
    }

    public String checkFirstNameRequiredMessage() {
    }

    public String checkEmailRequiredMessage() {
    }

    public String checkPasswordRequiredMessage() {
    }

    public String checkAliasRequiredMessage() {
    }

    public String checkAddressRequiredMessage() {
    }

    public String checkCityRequiredMessage() {
    }

    public String checkCountryInvalidMessage() {
    }

    public String checkCountryErrorMessage() {
    }
}
