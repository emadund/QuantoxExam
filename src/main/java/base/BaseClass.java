package base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseClass extends UtilsBaseClass{

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> testPlatform = new ThreadLocal<>();
    private final String fs = File.separator;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest

    /*
    Creating html report after all automation tests run.
     */
    public void creatingReport () {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + fs + "reports" + fs + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Test Done By", "Marko Dunda");
    }
    /*
    Setting up environment for performing all our tests
     */
    @BeforeMethod

    protected void initializaton (@Optional("chrome") String browser, Method m, ITestResult iTestResult) {
        logger = extent.createTest(m.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        wdWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }
    /*
     * After tests are done, we need to close our webdriver. And to complete our report
     * In case you want to let browser open to check out what happened at the end, please put this method under comment!
     */

    @AfterMethod

    protected void tearDown() {
        driver.quit();
    }


}
