package PageObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;


public class HomePageAmazon extends Base {

	@FindBy(xpath="//div[@class='nav-search-field ']//input")
	WebElement SearchBox;

	@FindBy(xpath="//input[@value='Go']")
	WebElement SearchButton;

    WebDriver driver;


//Loading and initializing the objects
    public HomePageAmazon(final WebDriver driver)
  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
  }


    public ProductListingPage SearchProduct(final String product) throws InterruptedException
  {
	  System.out.println("Inside SearchProduct method");
        SearchBox.clear();
        SearchBox.sendKeys(product, Keys.ENTER);
	 // SearchBox.sendKeys("Headphones");

        return (new ProductListingPage(driver));

}



}
