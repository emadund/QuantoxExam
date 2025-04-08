package page;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.Constants;

import static tools.Constants.BESTSELLERS;

public class EverandPage extends BaseClass {
    @FindBy (css="[data-testid=\"Ebooks\"]")
    WebElement ebooks;



    public EverandPage() {

        PageFactory.initElements(getDriver(), this);
    }

    public void clickOnWishedCategory (String category) {
        clickOnItemOfDropDownMenu(ebooks,BESTSELLERS);
    }





}
