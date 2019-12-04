package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorrosivesPage extends BasePage {

    public CorrosivesPage() {
        PageFactory.initElements(driver, this);
    }

    @FindAll({
            @FindBy(xpath = "//ul[@class='rsmFlow rsmLevel rsmOneLevel']//a")
    })
    List<WebElement> breadCrumbs;

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Our Products", "Trailers", "Tank Trailers", "Stainless Steel", "Corrosives"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }

    public List<WebElement> getBreadCrumbs() {
        return breadCrumbs;
    }
}
