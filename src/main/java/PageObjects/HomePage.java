package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class HomePage extends Base {

    @FindBy(xpath = "//input")
    WebElement emailBox;

    @FindBy(id = "enterimg")
    WebElement goButton;

    // Loading and initializing the objects
    public HomePage() {

        PageFactory.initElements(driver, this);


    }

    public registrationPage enterEmail() {

        emailBox.sendKeys("abcd@gmail.com");
        goButton.click();
        return (new registrationPage());


    }

}
