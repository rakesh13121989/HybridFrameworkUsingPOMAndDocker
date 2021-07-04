package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Base.Base;
import Utility.Utilities;

public class AddToCartTestcases3 extends Base {


	public Boolean status;


	//Search for Headphone and verify that atleast one product with Best Seller is displayed after search
    @Test
    public void SearchProduct() throws InterruptedException
    {
    	test=extent.createTest("Search product");
        productListingPage = homePageAmazon.SearchProduct("Headphones");
        Thread.sleep(3000);
    	status=Utilities.VerifyElementPresent(productListingPage.BestSellerProducts.get(0));
    	Assert.assertTrue(status);
        Utilities.WaitForPagetoLoad(driver);

    }

    @Test
    public void SearchProduct1() throws InterruptedException {
        test = extent.createTest("Search product2");
        productListingPage = homePageAmazon.SearchProduct("earphone");
        Thread.sleep(3000);
        status = Utilities.VerifyElementPresent(productListingPage.BestSellerProducts.get(0));
        Assert.assertTrue(status);
        Utilities.WaitForPagetoLoad(driver);

    }
   // This test is for adding all the Best selling products into cart
    @Test
    public void AddProductToCart() throws InterruptedException
    {
    	System.out.println("Inside AddProductToCart test");
        String CartItemCountString="";
    	test=extent.createTest("Add products to cart");
    //This loop will select all the best selling products one by one and will add to cart
    	for(int i=0;i<productListingPage.BestSellerProducts.size();i++)
    	{
    		//Get the products  one by one depending on the "i" value
    		final WebElement ele=productListingPage.BestSellerProducts.get(i);
    	    System.out.println("Inside For loop");
    	    //Scroll to the product
            Utilities.ScrollToElement(ele, driver);
    		System.out.println(i+1 +" Product selected , name of the product : " +ele.getText());
    		//Click the product link to open the product
    		ele.click();
            Utilities.WaitForPagetoLoad(driver);
    		//Click on add to cart button
            final Boolean cartButtonClickStatus = productDetailsPage.AddToCartButtonClick();
            final int count = 0;
            if (cartButtonClickStatus) {
            Utilities.WaitForPagetoLoad(driver);
    		//Below code is for validating the cart value after adding the product to cart
                CartItemCountString = count + 1 + " item";
    		final Boolean TextValidation= productDetailsPage.validateItemsCount(CartItemCountString);
    		softAssert.assertTrue(TextValidation);
            }
    		// navigate back to search result page
    		driver.navigate().back();
            Utilities.WaitForPagetoLoad(driver);

    	}


    }

    // This method will execute after every test method and status of the test case will be captured by ItestResult object
    // and based on the status report is generated and captured in extent report html file . If any fail will be there then
    // screenshot will be captured and will be included in the same html report with failed status of the particular test
    @AfterMethod
    public void ReportLogging( final ITestResult result) throws IOException {
        utilities.LogReport(result, test);
    	softAssert.assertAll();

    }
    //This method will execute once all the test execution is done

    @AfterClass
    public void endReport() {
        driver.quit();
    System.out.println("Inside EndReport(After Test) method");
     extent.flush();

    }
}
