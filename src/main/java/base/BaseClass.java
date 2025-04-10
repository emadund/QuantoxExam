package base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.block_third_party_cookies", true);
        prefs.put("profile.default_content_setting_values.plugins", 1);
        prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
        prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        // Enable Flash for this site        prefs.put("PluginsAllowedForUrls", "https://www.bet365.rs/#/HO/");
        prefs.put("PluginsAllowedForUrls", "https://www.bet365.rs/#/HO/");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.setExperimentalOption("prefs",prefs);
        options.addArguments("chrome.switches","--disable-extensions");
        options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--test-type");
        options.addArguments("--disable-bundled-ppapi-flash");
        options.addArguments("--disable-extensions");
        options.addArguments("--profile-directory=Default");
        options.addArguments("--disable-plugins-discovery");
        options.addArguments("--block-third-party-cookies");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");
        options.addArguments("--allow-outdated-plugins");
        driver = new ChromeDriver(options);
        /*
         * Tried with Firefox and Edge driver. Same thing happens with bet365 registration page.
         */
//        WebDriverManager.firefoxdriver().setup();
//        driver=new FirefoxDriver();
//        WebDriverManager.edgedriver().setup();
//        driver=new EdgeDriver();
        driver.manage().deleteAllCookies();
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
