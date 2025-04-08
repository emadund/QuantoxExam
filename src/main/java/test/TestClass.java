package test;

import base.BaseClass;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.BET365Page;
import page.EverandPage;
import page.FlashScoreHomePage;
import page.NavigationPage;
import tools.DIGITS;

import java.util.Random;

import static tools.Constants.*;
import static tools.DIGITS.SEVEN;
import static tools.DIGITS.SIX;

public class TestClass extends BaseClass {
    private FlashScoreHomePage flashScoreHomePage;
    private NavigationPage navigationPage;
    private EverandPage everandPage;
    private BET365Page bet365Page;
    private Random ran = new Random();


    @BeforeMethod
    public void initializationPageObjects() {
        navigationPage = new NavigationPage();
    }


    @Test(priority = 10, description = "Testing functionality of dark theme")

    public void flashScoreTheme() {
        flashScoreHomePage = new FlashScoreHomePage();
        printHeaderLogs("1. Dark/Light theme switch test");
        Reporter.log("Navigate to flashscore Home page", true);
        navigationPage.navigateToPage(FLASHSCORE_HOMEPAGE);
        checkPageIsReady();
        Reporter.log("Remove cookie", true);
        flashScoreHomePage.clickOnCookie();
        String firstColorHex = flashScoreHomePage.colorOfElement();
        Reporter.log("Background color of page in hex is: " + firstColorHex, true);
        flashScoreHomePage.clickOnMenu();
        Reporter.log("Click on dark theme, background of page should be changed", true);
        flashScoreHomePage.clickOnTheme();
        Reporter.log("Checking out if background color is swiched", true);
        Assert.assertNotEquals(firstColorHex, flashScoreHomePage.colorOfElement(), "background of home page didn't switch! Test 1 failed!");
        Reporter.log("Now the background of home page in hex is: " + flashScoreHomePage.colorOfElement(), true);
        Reporter.log("Test 1 passed!", true);
    }

    @Test(priority = 20, description = "Checking if footer app store redirects to another page")

    public void flashScoreApps() {
        Reporter.log("We use previous method for performing previous steps", true);
        this.flashScoreTheme();
        printHeaderLogs("2. App Store redirects to apple page test");
        Reporter.log("Scroll way down to the bottom of page", true);
        waitImplicit(2000);
        flashScoreHomePage.scrollToTheBottomOfThePage();
        Reporter.log("Click on app store", true);
        waitImplicit(2000);
        flashScoreHomePage.clickJsOnAppStore();
        // flashScoreHomePage.clickOnAppStore(); For some strange reason it puts back on the top of the page
        // flashScoreHomePage.clickOnAppStore(); It works after second click method, so it is better to use js click
        Reporter.log("Check out if it is redirected to another page");
        waitImplicit(2000);
        navigationPage.openNewTab();
        checkPageIsReady();
        Assert.assertTrue(getDriver().getCurrentUrl().startsWith(FLASHSCORE_RESULTS), "It didn't redirected where we expected. Test 2 failed!");
        Reporter.log("Test 2 passed!", true);
    }

    @Test(priority = 30, description = "Checking whether it redirects to wished sport's page and returning back")

    public void flashScoreTennis() {
        flashScoreHomePage = new FlashScoreHomePage();
        printHeaderLogs("3. Tennis page test");
        Reporter.log("Navigate to Flashscore Homepage");
        navigationPage.navigateToPage(FLASHSCORE_HOMEPAGE);
        checkPageIsReady();
        Reporter.log("Remove cookie", true);
        flashScoreHomePage.clickOnCookie();
        Reporter.log("From the top menu we choose tennis sport:", true);
        waitImplicit(2000);
        flashScoreHomePage.clickOnWishedSport(TENNIS);
        Reporter.log("Checking if it redirected to tennis page", true);
        waitImplicit(2000);
        checkPageIsReady();
        Assert.assertEquals(getDriver().getCurrentUrl(), FLASHSCORE_HOMEPAGE + "tennis/", "It didn't redirect to tennis page. Test 3 failed!");
        navigationPage.navigateBack();
        waitImplicit(2000);
        checkPageIsReady();
        Reporter.log("Checking if it returned back to home page", true);
        Assert.assertEquals(getDriver().getCurrentUrl(), FLASHSCORE_HOMEPAGE, "It didn't return to home page. Test 3 failed");
        Reporter.log("Test 3 passed!", true);
    }

    @Test(priority = 40, description = "Checking if choosing bestsellers redirects to right page")

    public void everand() {
        everandPage = new EverandPage();
        printHeaderLogs("4. Bestseller books page test");
        Reporter.log("Navigate to everand home page", true);
        navigationPage.navigateToPage(EVERAND);
        waitImplicit(2000);
        checkPageIsReady();
        Reporter.log("Hover over eBooks and pick Bestsellers", true);
        everandPage.clickOnWishedCategory(BESTSELLERS);
        waitImplicit(2000);
        checkPageIsReady();
        Reporter.log("Check if it redirected to submenu bestsellers/books", true);
        Assert.assertEquals(getDriver().getCurrentUrl(), EVERAND + "bestsellers/books", "It didn't redirect to bestseller page. Test 4 failed!");
        Reporter.log("Test 4 passed!", true);
    }

    @Test(priority = 50, description = "Checking mobile phone number field during registration at bet365 site")

    public void bet365Registration() {
        bet365Page = new BET365Page();
        printHeaderLogs("5. Bet365 registration via mobile phone number boundary values test");
        Reporter.log("Navigate to Bet365 Home page", true);
        navigationPage.navigateToPage(BET365);
        checkPageIsReady();
        Reporter.log("Click on cookie at the bottom",true);
        bet365Page.clickOnCookie();
        waitImplicit(1000);
        Reporter.log("Click on registration button",true);
        bet365Page.clickOnRegistration();
        waitImplicit(1000);
        Reporter.log("Fill all other mandatory fields except phone number:", true);
        bet365Page.fillMandatoryFields();
        waitImplicit(1000);
        /*
         * For Serbia MSISDN (mobile phone numbers) are with prefixes from 60 to 60 either 6-digits or 7-digits numbers
         * Lets test random samples of 6 digit-numbers portion between 60 000 000 and 69 999 999
         * We will try all prefixes from 60 to 69
         */
        Reporter.log("Let's check first positive test cases. 6-digit numbers: ", true);
        generatingRandomNumber(SIX);
        Reporter.log("Accepts all prefixes and random 6-digit numbers",true);
        Reporter.log("Let's check positive test case with 7-digit numbers",true);
        waitImplicit(1000);
        generatingRandomNumber(SEVEN);
        Reporter.log("Accepts all prefixes and random 7-digit numbers",true);
        /*
         * Phone number fields also accepts leading 0 digit which is excessive but it is working so it is positive TC
         */
        Reporter.log("Checking 6 digit numbers with leading 0",true);
        waitImplicit(1000);
        generatingNumbersLeadingZero(SIX);
        Reporter.log("Accepts all prefixes and random 6-digit numbers with leading 0",true);
        Reporter.log("Checking 7 digit numbers with leading 0", true);
        waitImplicit(1000);
        generatingNumbersLeadingZero(SEVEN);
        Reporter.log("Accepts all prefixes and random 7-digit numbers with leading 0",true);
        waitImplicit(1000);
        Reporter.log("Let's now check bottom boundary value with 60 0000000",true);
        Long boundaryValue=600000000L;
        bet365Page.fillPhoneField(String.valueOf(boundaryValue));
        bet365Page.clickOnSubmit();
        Assert.assertTrue(bet365Page.getIdentificationDocumentText().startsWith("Za državljane Republike Srbije"));
        bet365Page.clickOnIdentificationCard();
        Reporter.log("Now, lets decrease input below bottom boundary value and check out an error field",true);
        waitImplicit(1000);
        bet365Page.fillPhoneField(String.valueOf(--boundaryValue));
        Assert.assertEquals(bet365Page.getErrorText(),"Obavezno","It allows phone number which shouldn't happen");
        Reporter.log("Testing bottom boundary value is passed!",true);
        waitImplicit(1000);
        Reporter.log("Now let's check top boundary vaule with 699999999",true);
        boundaryValue=699999999L;
        bet365Page.fillPhoneField(String.valueOf(boundaryValue));
        bet365Page.clickOnSubmit();
        Assert.assertTrue(bet365Page.getIdentificationDocumentText().startsWith("Za državljane Republike Srbije"));
        Reporter.log("Testing with top boundary value is passed!",true);
        waitImplicit(1000);
        Reporter.log("Now, let's test invalid top boundary value increasing input: ",true);
        bet365Page.fillPhoneField(String.valueOf(++boundaryValue));
        bet365Page.clickOnSubmit();
        Assert.assertEquals(bet365Page.getErrorText(),"Obavezno","It allows phone number which shouldn't happen");
        Reporter.log("Testing bottom boundary value is passed!", true);
        waitImplicit(1000);
        Reporter.log("Let's test with empty field left.",true);
        bet365Page.fillPhoneField("");
        bet365Page.clickOnSubmit();
        Assert.assertEquals(bet365Page.getErrorText(),"Obavezno","It allows phone number which shouldn't happen");
        Reporter.log("Test 5 passed! ");
    }


    private void printHeaderLogs(String testNameAndDescription) {
        System.out.println("===========================================================================");
        System.out.println(testNameAndDescription);
        System.out.println("===========================================================================");

    }

    private String randomSixOrSevenDigitNumber(DIGITS digits) {
        String temp = "";
        switch (digits) {
            case SIX:
                for (int i = 1; i <= 6; i++) {
                    temp += String.valueOf(ran.nextInt(10));
                }
                return temp;

            case SEVEN:
                for (int i = 1; i <= 7; i++) {
                    temp += String.valueOf(ran.nextInt(10));
                }
                return temp;
        }
        return temp;
    }
    private void generatingRandomNumber(DIGITS digits) {
        for (int i = 0; i <= 9; i++) {
            bet365Page.fillPhoneField("6" + i + "" + randomSixOrSevenDigitNumber(digits));
            waitImplicit(1000);
            bet365Page.clickOnSubmit();
            Assert.assertTrue(bet365Page.getIdentificationDocumentText().startsWith("Za državljane Republike Srbije"));
            bet365Page.clickOnPersonalInfo();
        }
    }
    private void generatingNumbersLeadingZero(DIGITS digits) {
        for (int i = 0; i <= 9; i++) {
            bet365Page.fillPhoneField("06" + i + "" + randomSixOrSevenDigitNumber(digits));
            waitImplicit(1000);
            bet365Page.clickOnSubmit();
            Assert.assertTrue(bet365Page.getIdentificationDocumentText().startsWith("Za državljane Republike Srbije"));
            bet365Page.clickOnPersonalInfo();
        }
    }
}
