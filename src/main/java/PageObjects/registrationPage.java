package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.Base;

public class registrationPage extends Base {

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@type='tel']")
    public WebElement phone;

    WebDriver driver;

    // Loading and initializing the objects
    public registrationPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void registration() {
        System.out.println("Inside registration method");
        firstName.sendKeys("Raj");
        lastName.sendKeys("Malhotra");
        Assert.assertEquals(driver.getTitle(), "Register");

    }

    public void emailAndPhone() {
        System.out.println("Inside registration method");
        emailBox.sendKeys("Raj@gmail.com");
        phone.sendKeys("9620381982");
        Assert.assertEquals(driver.getTitle(), "Register");

    }

}
