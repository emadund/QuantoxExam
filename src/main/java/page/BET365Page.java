package page;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BET365Page extends BaseClass {

    @FindBy (css = ".rcc-33 .rcc-ef")
    WebElement cookie;

    @FindBy (css =".hm-MainHeaderRHSLoggedOutWide_Badge.hm-MainHeaderRHSLoggedOutWide_Join")
    WebElement register;

    @FindBy (id="email")
    WebElement email;

    @FindBy (id="first_name")
    WebElement firstName;

    @FindBy (id="last_name")
    WebElement lastName;

    @FindBy (css=".svn-DateInputComponent_InputRow input[placeholder=\'Dan\']")
    WebElement day;

    @FindBy (css=".svn-DateInputComponent_InputRow input[placeholder=\'Mesec\']")
    WebElement month;

    @FindBy (css=".svn-DateInputComponent_InputRow input[placeholder=\'Godina\']")
    WebElement year;

    @FindBy (linkText = "Nastavi")
    WebElement submit;

    @FindBy (xpath = "//div[@class='oam-FieldInternationalPhone_InputRowContainer ']/input[@id=\"tel\"]")
    WebElement phone;

    @FindBy (xpath = "//div[@class='oam-FieldTab_TabTitle '][1]")
    WebElement personalInfo;

    @FindBy (xpath = "//div[@class='oam-FieldTab_TabTitle '][2]")
    WebElement identificationCard;

    @FindBy (css=".fml-ErrorMessage.svn-FieldInputTextPlaceholder_ErrorMessage")
    WebElement errorMessage;

    @FindBy (css=".oam-FieldGroupTabPage .oam-FieldGroupWithParagraph_Paragraph")
    WebElement identificationText;


    public BET365Page () {
        PageFactory.initElements(getDriver(),this);
    }

    public void clickOnRegistration() {
        clickOnElement(register);
    }

    public void clickOnCookie () {
        try {  clickOnElement(cookie); }
        catch (Exception e) {
            System.out.println("Cookie didn't appear so we move on");
        }
    }

    public void clickOnPersonalInfo() {
        clickOnElement(personalInfo);
    }

    public void clickOnIdentificationCard() {
        clickOnElement(identificationCard);
    }

    public BET365Page fillMandatoryFields() {
        fillTextElement(email,"dundaetf@gmail.com");
        waitImplicit(500);
        fillTextElement(firstName,"Marko");
        waitImplicit(500);
        fillTextElement(lastName,"Dunda");
        waitImplicit(500);
        fillTextElement(day,"11");
        waitImplicit(500);
        fillTextElement(month,"8");
        waitImplicit(500);
        fillTextElement(year,"1983");
        waitImplicit(500);
        return this;
    }

    public void clickOnSubmit() {
        clickOnElement(submit);
    }
    public void fillPhoneField(String number) {
        fillTextElement(phone,number);
    }

    public String getErrorText() {
        return getTextElement(errorMessage);
    }

    public String getIdentificationDocumentText() {
        return getTextElement(identificationText);
    }





}
