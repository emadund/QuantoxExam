package page;

import base.BaseClass;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class NavigationPage extends BaseClass {

    public NavigationPage() {
        PageFactory.initElements(getDriver(),this);
    }

    // Actions:

    /*
     * Navigate to provided url
     * @param url - to access
     */
    public void navigateToPage(String url){
        getDriver().get(url);
        checkPageIsReady();
    }
    /*
     * Refresh the current page
     */
    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    /*
     * Open a new tab in window
     */
    public void openNewTab(){
            wdWait.until(ExpectedConditions.numberOfWindowsToBe(2));
            ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
            getDriver().switchTo().window(tabs.get(1));
        }
    /*
     * Move to previous tab
     * @param tab - specify which tab to move. 0 is for first
     */
    public void moveToPreviousTab (int tab) {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tab));
    }
    public void navigateBack() {
        getDriver().navigate().back();
    }
}
