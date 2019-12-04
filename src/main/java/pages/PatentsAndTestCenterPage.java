package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatentsAndTestCenterPage extends ContentPage {

    public PatentsAndTestCenterPage() {
        PageFactory.initElements(driver, this);
    }

//    @FindAll({
//            @FindBy(xpath = "//div[contains(@id, 'Breadcrumb')]//a")
//    })
//    List<WebElement> breadCrumbs;

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Tradition of Innovation",
            "Patents and R&D Test Center"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }

//    public List<WebElement> getBreadCrumbs() {
//        return breadCrumbs;
//    }
}
