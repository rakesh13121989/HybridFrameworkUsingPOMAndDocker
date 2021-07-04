package Base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObjects.HomePage;
import PageObjects.HomePageAmazon;
import PageObjects.ProductDetailsPage;
import PageObjects.ProductListingPage;
import PageObjects.registrationPage;
import Utility.Utilities;
import Utility.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    // public static WebDriver driver ;
    public static EventFiringWebDriver e_driver;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public ITestResult result;
    public static String workingDirectory;
    public static String driverPath;
    public static String pageLoadTimeOut;
    public HomePage homePage;
    public HomePageAmazon homePageAmazon;
    public ProductDetailsPage productDetailsPage;
    public ProductListingPage productListingPage;
    public registrationPage registrationPage;

    public SoftAssert softAssert;
    public WebDriver driver;
    public Utilities utilities;

    @BeforeClass
    public void setUp() throws IOException {
        driver = initialize();
        homePageAmazon = new HomePageAmazon(driver);
        homePage = new HomePage(driver);
        registrationPage = new registrationPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        productListingPage = new ProductListingPage(driver);
        softAssert = new SoftAssert();
        utilities = new Utilities(driver);
    }

    protected WebDriver initialize() throws IOException

    {

        workingDirectory = System.getProperty("user.dir");
        final String BrowserName = Utilities.GetPropertyValue("Browser");
        final String SiteURL = Utilities.GetPropertyValue("SiteURL");
        pageLoadTimeOut = Utilities.GetPropertyValue("PageLoadTimeOutTimeInSeconds");



        if (BrowserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (BrowserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        // Registering listener to driver object for logging purpose
        final EventFiringWebDriver newDriver = new EventFiringWebDriver(driver);
        // creating object of class WebEventListener which is created under Utility
        // package
        final WebEventListener newEventListner = new WebEventListener();
        newDriver.register(newEventListner);
        driver = newDriver;

        driver.get(SiteURL);
        driver.manage().window().maximize();
        ReportSetUp();
        return driver;

    }

    public void ReportSetUp() {
        System.out.println("Inside ReportSetUp Method");

        final String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        final String dateFolder = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        htmlReporter = new ExtentHtmlReporter(
                System.getProperty("user.dir") + "/TestResult" + "/Reports/" + dateFolder + "/" + dateName + ".html");
        System.out.println("Report will be saved on : " + htmlReporter.getFilePath());
        htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
        htmlReporter.config().setReportName("Amazon Search Headphone Testing"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    // This method will execute after every test method and status of the test case
    // will be captured by ItestResult object
    // and based on the status report is generated and captured in extent report
    // html file . If any fail will be there then
    // screenshot will be captured and will be included in the same html report with
    // failed status of the particular test
    @AfterMethod
    public void ReportLogging(final ITestResult result) throws IOException {
        test = extent.createTest(result.getMethod().getMethodName());
        utilities.LogReport(result, test);
        softAssert.assertAll();

    }
    // This method will execute once all the test execution is done

    @AfterClass
    public void endReport() {
        driver.quit();
        System.out.println("Inside EndReport(After Test) method");
        extent.flush();

    }
}
