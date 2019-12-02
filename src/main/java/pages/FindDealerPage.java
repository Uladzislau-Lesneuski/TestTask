package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FindDealerPage extends BasePage {
    public FindDealerPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "CompanyName")
    WebElement searchField;

    @FindBy (xpath = "//h2/a[contains(text(), 'Truck')]")
    WebElement truckItem;


    public boolean isTruckItemDisplayed() {
        return truckItem.isDisplayed();
    }

    public void searchItem(String searchItem) {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(searchItem);
    }
}
