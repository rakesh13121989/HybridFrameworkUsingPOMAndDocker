package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base;
import Utility.Utilities;

public class RegistrationPageTestCase extends Base {




    @Test(priority = 0)
    public void registrationPageTestCases() {

        homePage.enterEmail();
        registrationPage.emailAndPhone();
        Assert.assertTrue(Utilities.VerifyElementPresent(registrationPage.firstName));
        Assert.assertTrue(Utilities.VerifyElementPresent(registrationPage.lastName));

    }








}
