package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuelEconomyPage extends BasePage {

    public FuelEconomyPage() {
        PageFactory.initElements(driver, this);
    }

    @FindAll({
            @FindBy(xpath = "//div[contains(@id, 'Breadcrumb')]//a")
    })
    List<WebElement> breadCrumbs;

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "We Are Wabash",
            "Corporate Responsibility", "Sustainability", "Fuel Economy"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }

    public List<WebElement> getBreadCrumbs() {
        return breadCrumbs;
    }
}
