package Utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.Base;

public class Utilities extends Base {

    WebDriver driver;

    // Loading and initializing the driver

    public Utilities(final WebDriver driver) {

        this.driver = driver;
    }

	public static String GetPropertyValue(final String PropertyName) throws IOException
	{
		final Properties props= new Properties();
        System.out.println("Inside GetPropertyValue method");
        final File file = null;
        try {
            // final ClassLoader classLoader =
            // Thread.currentThread().getContextClassLoader();
            // file = new File(classLoader.getResource("Config.properties").toURI());
            // final URL url = Utilities.class.getResource("/Config.properties");
            // file = new File(url.getPath());
            // props.load(new FileInputStream(file));


            final InputStream inputstream = Utilities.class.getResourceAsStream("/Config.properties");
            props.load(inputstream);

        } catch (final Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
        }



		//String Androidversion=props.getProperty("androidVersion");
        System.out.println("Property value of " + PropertyName + "=" + props.getProperty(PropertyName));
        return (props.getProperty(PropertyName));

    }

    public String getScreenshot(final String screenshotName) throws IOException {
		  final String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  final String dateFolder=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		  final TakesScreenshot ts = (TakesScreenshot) driver;
		  final File source = ts.getScreenshotAs(OutputType.FILE);

		  // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        final String destination = System.getProperty("user.dir") + "/src/main/resources" + "/Screenshots/"
                + dateFolder + "/" + screenshotName + dateName + ".png";
		  final File finalDestination = new File(destination);
		  FileUtils.copyFile(source, finalDestination);
		  return destination;
		 }

    public void LogReport(final ITestResult result, final ExtentTest test)
            throws IOException {
		 System.out.println("Inside LogReport Method : ");

		 if (result.getStatus() == ITestResult.FAILURE) {

			  System.out.println(result.getName() +": Test is failed ");
		   test.log(Status.FAIL, result.getName() +" TEST CASE IS FAILED " ); // to add name in extent report
		   test.log(Status.FAIL, "Failed Message:  " + result.getThrowable()); // to add error/exception in extent report
            final String screenshotPath = getScreenshot(result.getName());
		   System.out.println("Screenshot willl be saved in path :"+screenshotPath);
		   test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		  } else if (result.getStatus() == ITestResult.SKIP) {
			  System.out.println(result.getName() +": Test is skipped ");
		   test.log(Status.SKIP, result.getName() +" TEST CASE IS Skipped ");
		  }
		  else if (result.getStatus() == ITestResult.SUCCESS) {
			  System.out.println(result.getName() +": Test is Passed ");
		   test.log(Status.PASS, result.getName() +" TEST CASE IS Passed ");
		  }

		 }

	 public static boolean VerifyElementPresent(final WebElement ele)
	 {
		try
		{
			ele.isDisplayed();
			return true;
		}
		catch(final Exception e){
			return false;
		}

	 }

    public static void ScrollToElement(final WebElement ele, final WebDriver driver) {

		 final JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView(true);",ele);
	 }

    public static void WaitForPagetoLoad(final WebDriver driver)
	 {
		 driver.manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeOut), TimeUnit.SECONDS);
	 }
	 public static Boolean ValidateTextOfElement(final WebElement ele , final String ExpectedText)
	 {
		if(ele.getText().contains(ExpectedText)) {
			 System.out.println(ExpectedText + " found sucessfully");
			 return true;
		}
		else {
			 System.out.println(ExpectedText + " doesn't match with : "+ele.getText());
			return false;

		}

	 }


}
