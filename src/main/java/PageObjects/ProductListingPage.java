package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductListingPage {

	@FindBy(xpath="//span[text()='Best Seller']/ancestor::div[@class='sg-row']/following-sibling::div//h2//span")
	public List<WebElement> BestSellerProducts;

    WebDriver driver;

//Loading and initializing the objects
    public ProductListingPage(final WebDriver driver)
  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
  }




}
