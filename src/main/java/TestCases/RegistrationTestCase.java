package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base;
import Utility.Utilities;

public class RegistrationTestCase extends Base {




    @Test(priority=0)
    public void emailEnter()
    {

        homePage.enterEmail();
        Assert.assertTrue(Utilities.VerifyElementPresent(registrationPage.firstName));

    }

    @Test(priority = 1)
    public void registration() {

        registrationPage.registration();
        Assert.assertTrue(Utilities.VerifyElementPresent(registrationPage.lastName));
    }





}
