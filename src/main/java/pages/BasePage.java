package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webdriversingleton.WebDriverSingleton;
import java.util.List;
import java.util.Random;

public class BasePage {
    WebDriver driver = WebDriverSingleton.create();

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    @FindAll({
            @FindBy (xpath = "//ul[contains(@class, 'CustomMenu')]/li")
    })
    List<WebElement> mainMenuSections;

    @FindAll({
            @FindBy (xpath = "//ul[contains(@class, 'CustomMenu')]//li")
    })
    List<WebElement> dropDownItems;


    public void navigateThroughRandomItemsFromEachSection() {
        Actions builder = new Actions(driver);
        Random random = new Random();

        for (int i = 0; i < mainMenuSections.size(); i++) {
            builder.moveToElement(mainMenuSections.get(i));
            for (int j = 0; j < dropDownItems.size(); j++) {
                //int index = random.nextInt(dropDownItems.size() - 0) + 0;
                builder.moveToElement(dropDownItems.get(j)).build().perform();
            }
            break;

        }

    }
}
