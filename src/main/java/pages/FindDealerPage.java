package pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FindDealerPage extends BasePage {
    public FindDealerPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "CompanyName")
    WebElement companyNameField;

    @FindBy (id = "ContentPlaceHolderContent_C001_txtSourceZip")
    WebElement locationField;

    @FindBy (xpath = "//h2/a[contains(text(), 'Truck')]")
    WebElement truckItem;

    @FindBy (id = "ContentPlaceHolderContent_C001_lblDealerCount")
    WebElement quantityOfSearchItems;

    @FindBy (xpath = "//div[@class='noUi-handle noUi-handle-lower']")
    WebElement slider;

    @FindBy (xpath = "//div[@class='noUi-base']")
    WebElement sliderRadius;


    public boolean isTruckItemDisplayed() {
        return truckItem.isDisplayed();
    }

    public void searchItem(String searchItem) {
        wait.until(ExpectedConditions.visibilityOf(companyNameField));
        companyNameField.sendKeys(searchItem);
    }

    public int getSearchItemsQuantity() {
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));
        return Integer.parseInt(quantityOfSearchItems.getText().split(" ")[0]);
    }

    public void setSliderToMaxPosition() {
        builder.moveToElement(slider).clickAndHold()
                .moveByOffset((sliderRadius.getSize().getWidth()), 0)
                .release().perform();
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));
    }

    public void setSliderToDefaultPosition() {
        builder.moveToElement(slider).clickAndHold()
                .moveByOffset(-(sliderRadius.getSize().getWidth() / 2), 0)
                .release().perform();
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));
    }


    public void fillFieldsWithRandomData() {
        companyNameField.sendKeys(generateRandomString(10));
        locationField.sendKeys(generateRandomString(10));
    }
}
