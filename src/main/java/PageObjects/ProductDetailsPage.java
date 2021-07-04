package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductDetailsPage {
    WebDriver driver;

	@FindBy(xpath="//div[@data-feature-name='addToCart']")
	 WebElement AddToCartButton;



	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	public  WebElement ItemsCountinCart;

//Loading and initializing the objects
    public ProductDetailsPage(final WebDriver driver)
  {
        PageFactory.initElements(driver, this);
        this.driver = driver;

  }

    public boolean AddToCartButtonClick() {
        try {
            AddToCartButton.click();
            return true;
        } catch (final Exception e) {
            return false;
        }


 }

 public boolean validateItemsCount(final String ExpectedText) throws InterruptedException {
	 /*WebDriverWait wait= new WebDriverWait(driver,10);
	 wait.until(ExpectedConditions.visibilityOfElementLocated((By) ItemsCountinCart));*/
	 Thread.sleep(4000);
	 if(ItemsCountinCart.getText().contains(ExpectedText)) {
		 System.out.println(ExpectedText + " found sucessfully");
		 return true;
	}
	else {
		 System.out.println(ExpectedText + " doesn't match with : "+ItemsCountinCart.getText());
		return false;

	}
 }


}
