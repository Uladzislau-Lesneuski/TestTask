package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FindDealerPage extends BasePage {
    public FindDealerPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//input[@placeholder='Search Term']")
    WebElement searchTerm;

    @FindBy (xpath = "//input[contains(@placeholder, 'State')]")
    WebElement locationField;

    @FindBy (xpath = "//h2/a")
    WebElement foundItems;

    @FindBy (xpath = "//span[contains(@id, 'DealerCount')]")
    WebElement quantityOfSearchItems;

    @FindBy (xpath = "//div[@class='range-slider']//div[contains(@class, 'handle')]")
    WebElement slider;

    @FindBy (xpath = "//div[contains(@class, 'range')]/div[contains(@class, 'base')]")
    WebElement sliderRadius;

    @FindAll({
            @FindBy (xpath = "//span[contains(@class, 'ToggleCheckbox')]")
    })
    List <WebElement> allCheckboxes;

    @FindBy (id = "clear")
    WebElement clearAllFilters;

    @FindBy (xpath = "//div[@class='rs_tooltip']/span")
    WebElement sliderValue;

    public boolean isResultsContainEnteredString(String searchQuery) {
        wait.until(ExpectedConditions.visibilityOf(foundItems));
        System.out.println(foundItems.getText());
        return foundItems.getText().toLowerCase().contains(searchQuery);
    }

    public void enterSearchString(String searchString) {
        wait.until(ExpectedConditions.visibilityOf(searchTerm));
        searchTerm.sendKeys(searchString);
    }

    public int getSearchItemsQuantity() {
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));
        return Integer.parseInt(quantityOfSearchItems.getText().split(" ")[0]);
    }

    public void setSliderToMaxPosition() {
        builder.moveToElement(slider).clickAndHold()
                .moveByOffset((sliderRadius.getSize().getWidth()), 0)
                .release().perform();

        wait.until(ExpectedConditions.invisibilityOf(quantityOfSearchItems));
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));
    }

    public void setSliderToDefaultPosition() {
        builder.moveToElement(slider).clickAndHold()
                .moveByOffset( - (sliderRadius.getSize().getWidth() / 2), 0)
                .release().perform();

        wait.until(ExpectedConditions.invisibilityOf(quantityOfSearchItems));
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));
    }

    public void fillFieldsWithRandomData() {
        searchTerm.sendKeys(generateRandomString(10));
        locationField.sendKeys(generateRandomString(10));
    }

    public void checkAllCheckboxes() {
        for (WebElement checkboxes: allCheckboxes) {
            checkboxes.click();
        }
    }

    public void clearFilters() {
        clearAllFilters.click();
    }

    public boolean isEachCheckboxUnchecked() {
        boolean flag = false;
        for (WebElement checkboxes: allCheckboxes) {
            if (!checkboxes.isSelected())
                flag = true;
            else
                flag = false;

        }
        return flag;
    }

    public boolean isEachSearchFieldEmpty() {
        if ((searchTerm.getAttribute("value").isEmpty()) && (locationField.getAttribute("value").isEmpty()))
            return true;
        else
            return false;
    }

    public boolean isSliderInDefaultPosition() {
        return (Integer.parseInt(sliderValue.getText().split(" ")[0]) == 500);
    }

    public boolean isSearchWithRandomDataGetEmptyResult() {
        clearAllFilters.click();
        searchTerm.sendKeys(generateRandomString(10));
        wait.until(ExpectedConditions.invisibilityOf(quantityOfSearchItems));
        wait.until(ExpectedConditions.visibilityOf(quantityOfSearchItems));

        if (Integer.parseInt(quantityOfSearchItems.getText().split(" ")[0]) == 0)
            return true;
        else
            return false;
    }
}
