package Base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObjects.HomePage;
import PageObjects.registrationPage;
import Utility.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ITestResult result;


    public static String pageLoadTimeOut = "60";

    public static WebDriver driver;

    public static HomePage homePage;
    public static registrationPage regisPage;
    public static Utilities utilities;


    public static void getDriver(final String Browser) {

        if (Browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
    }
        else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get("http://demo.automationtesting.in/Index.html");
        driver.manage().window().maximize();
        ReportSetUp();

    }

    public static void ReportSetUp() {
        System.out.println("Inside ReportSetUp Method");

        final String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        final String dateFolder = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        htmlReporter = new ExtentHtmlReporter(
                System.getProperty("user.dir") + "/TestResult" + "/Reports/" + dateFolder + "/" + dateName + ".html");
        System.out.println("Report will be saved on : " + htmlReporter.getFilePath());
        htmlReporter.config().setDocumentTitle("Automation Report"); // Title of report
        htmlReporter.config().setReportName("Testing Report"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }




}
