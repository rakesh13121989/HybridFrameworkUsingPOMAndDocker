package TestCases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Base.Base;

public class RegistrationTestCase extends Base {




    @Test(priority=0)
    public void emailEnter()
    {

        homePage.enterEmail();


    }

    @Test(priority = 1)
    public void registration() {

        registrationPage.registration();

    }



    // This method will execute once all the test execution is done

    @AfterClass
    public void endReport() {
        System.out.println("Inside EndReport(After Test) method");
        driver.quit();
        extent.flush();

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


    }


}
