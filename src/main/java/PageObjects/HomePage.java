package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class HomePage extends Base {

    @FindBy(xpath = "//input")
    WebElement emailBox;

    @FindBy(id = "enterimg")
    WebElement goButton;

    WebDriver driver;

    // Loading and initializing the objects
    public HomePage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void enterEmail() {

        emailBox.sendKeys("abcd@gmail.com");
        goButton.click();



    }

}
