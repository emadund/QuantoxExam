package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import javax.swing.*;

public class UtilsBaseClass {

    protected static WebDriver driver;
    protected static WebDriverWait wdWait;
    protected static JavascriptExecutor js;



    public void setDriver() {
        if (driver==null) {
            driver = new ChromeDriver();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    /*
     * To check if page is fully loaded
     */

    protected static void checkPageIsReady() {
        JavascriptExecutor js = null;
        if (driver != null) {
            js = (JavascriptExecutor) driver;
        }
        //This loop will rotate for 25 times to check If page Is ready after every 2 second.
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Hit problem while waiting for JavaScript to load: " + e.getMessage());
            }
            //To check page ready state.
            if (js != null && js.executeScript("return document.readyState").toString().equals("complete")) {

                break;
            }
        }
    }

    /*
     * This we will use if not regular click is possible
     */
    protected void jsElementClick(WebElement element){

        try { wdWait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    /*
     * Setting implicit fixed time for waiting for program to continue
     */

    protected void waitImplicit(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    /*
     * Checking if element is showing up on page
     */
    protected boolean isElementDisplayed(WebElement element) {
        try { wdWait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }
    /*
     * Simple click on element after it appears
     */

    protected void clickOnElement(WebElement element) {
        try {
            wdWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
        catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /*
     * Getting text written in element
     */
    protected String getTextElement(WebElement element) {
        try {
            wdWait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "Element not found!";
        }
    }
    /*
     * Hover over dropdown and click on target element
     */
    protected void clickOnItemOfDropDownMenu (WebElement dropdown, String category) {
        try {
            Actions hover = new Actions(driver);
            wdWait.until(ExpectedConditions.elementToBeClickable(dropdown));
            hover.moveToElement(dropdown).build().perform();
            waitImplicit(1000);
            WebElement target = getDriver().findElement(By.linkText(category));
            wdWait.until(ExpectedConditions.elementToBeClickable(target));
            clickOnElement(target);
        }
        catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    /*
     * Clear and fill wished text in text field or text area element
     */
    protected void fillTextElement (WebElement element,String text) {
        try {
            wdWait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    /*
     * Scrolling to the element and put it in center of moment view the page
     */
    protected  void scrollToElementCenter(WebElement element) {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }
    }
    /*
    In case of issues with regular click, we will use this method
     */
    protected void jsClick (WebElement element) {
        try {
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    /*
     * Scroll to the bottom of the page
     */
    public void scrollToTheBottomOfThePage() {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

    /*
     * Scroll to the top of the page
     */
    public void scrollToTheTopOfThePage() {
        js.executeScript("window.scrollTo(0, 0);", "");
    }
    /*
     * Scroll to custom amount of pixels.
     * @param pixels: Minus values are for scroll up!
     */
    public void scrollCustom(int pixels) {
        js.executeScript("window.scrollBy(0,"+pixels+")","");
    }

}
