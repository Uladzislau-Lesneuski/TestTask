package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriversingleton.WebDriverSingleton;

import java.util.*;

public class BasePage {
    WebDriver driver = WebDriverSingleton.create();

    public BasePage() {
        PageFactory.initElements(driver, this);
    }
    WebDriverWait wait = new WebDriverWait(driver, 20);

    Actions builder = new Actions(driver);

    @FindBy (css = "a[href='/we-are-wabash']")
    WebElement weAreWabashLink;

    @FindBy (css = "a[href='/tradition-of-innovation']")
    WebElement traditionOfInnovationLink;

    @FindBy (css = "a[href='/our-products']")
    WebElement ourProductsLink;

    @FindBy (css = "a[href='/our-brands']")
    WebElement ourBrandsLink;

    @FindBy (css = "a[href='/work-with-wabash']")
    WebElement workWithWabashLink;

    @FindBy (css = "a[href='/timeline']")
    WebElement timeLineLink;

    @FindBy (css = "a[href='/location-search']")
    WebElement locationSearchLink;

    @FindAll({
            @FindBy (css = "li.dropdown, a[href='/timeline']")
           // @FindBy (xpath = "//ul[contains(@class, 'CustomMenu')]/li")
    })
    List<WebElement> mainMenuSections;

    @FindAll({
            @FindBy (css = "ul[id*='childNodesContainer'] a")
            //@FindBy (xpath = "//ul[contains(@id, 'childNodesContainer')]//li")
    })
    List<WebElement> dropDownItems;

    @FindBy (css = "a[href='/small-menu/contact']")
    WebElement contactPageLink;

    @FindBy (css = "a[href='/location-search/dealer']")
    WebElement dealerLink;

    public void navigateThroughRandomItemsFromEachSection() {
        Random random = new Random();
        List<WebElement> childItems;
        int count = 0;


        for (WebElement sections: mainMenuSections) {

            System.out.println("Section name: " + sections.getText() + "->");
            builder.moveToElement(sections).perform();

            childItems = sections.findElements(By.cssSelector("ul[id*='childNodesContainer'] a"));
            //childItems = sections.findElements(By.xpath("//ul[contains(@id, 'childNodesContainer')]//a"));

            for (int i = 0; i < childItems.size(); i++) {
                builder.moveToElement(childItems.get(i)).perform();
                System.out.print("***" + childItems.get(i).getText() + " : ");
                System.out.println();
                count++;
            }

            int range = count;

//            int index = random.nextInt(range - 0) + 0;
//
//            childItems.get(index).click();
//            driver.navigate().back();

            System.out.println("Number of subelements is " + range);

            System.out.println();
            count = 0;

        }
        System.out.println();
    }

    public void goToContactPage() {
        contactPageLink.click();
    }

    public void goToDealerPage() {
        builder.moveToElement(locationSearchLink).click(dealerLink).perform();
    }
}
