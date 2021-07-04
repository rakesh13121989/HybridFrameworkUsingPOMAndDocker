package TestCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base;
import Utility.Utilities;

public class AddToCartTestcases extends Base {


	public Boolean status;


	//Search for Headphone and verify that atleast one product with Best Seller is displayed after search
    @Test
    public void SearchProduct() throws InterruptedException
    {

        productListingPage = homePageAmazon.SearchProduct("Headphones");
        Thread.sleep(3000);
    	status=Utilities.VerifyElementPresent(productListingPage.BestSellerProducts.get(0));
    	Assert.assertTrue(status);
        Utilities.WaitForPagetoLoad(driver);

    }

    @Test
    public void SearchProduct1() throws InterruptedException {

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


}
