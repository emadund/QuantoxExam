package page;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlashScoreHomePage extends BaseClass {

    // All relevant locators:

    @FindBy (css="#onetrust-button-group-parent #onetrust-button-group #onetrust-accept-btn-handler")
    WebElement cookieAccept;

    @FindBy (css="#hamburger-menu div[role='button']")
    WebElement menu;

    @FindBy (id="theme-switcher")
    WebElement theme;

    @FindBy (css=".container")
    WebElement container;

    @FindBy (css="[title='iPhone/iPad application']")
    WebElement appStore;

    public FlashScoreHomePage() {
        PageFactory.initElements(driver,this);
    }

    // Actions:

    public void clickOnCookie() {
        clickOnElement(cookieAccept);
    }
    public void clickOnMenu() {
        clickOnElement(menu);
    }

    public void clickOnTheme() {
        clickOnElement(theme);
    }

    /*
     * Returning the hex value of color, instead of rgba value
     */

    public String colorOfElement() {
        wdWait.until(ExpectedConditions.visibilityOf(container));
        String color = container.getCssValue("color");
        String hex= Color.fromString(color).asHex();
        return hex;
    }

    //Regular click on App Store icon but it drags onto top page for some reason
    public void clickOnAppStore() {
        clickOnElement(appStore);
    }

    public void clickJsOnAppStore () {
        jsClick(appStore);
    }

    /*
     * Clicking on whished sport
     * @param - sport
     */
    public void clickOnWishedSport (String sport) {
        WebElement element = getDriver().findElement(By.cssSelector(".menuTop__items a[href='/"+sport+"/']"));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


}
