package page;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class EverandPage extends BaseClass {

    // locators:
    @FindBy (css="[data-testid=\"Ebooks\"]")
    WebElement ebooks;



    public EverandPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Actions:

    /*
     * Hovering on dropdown menu and clicking on wished category
     * @param - category
     */
    public void clickOnWishedCategory (String category) {
        clickOnItemOfDropDownMenu(ebooks,category);
    }

}
