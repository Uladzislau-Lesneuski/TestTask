package pages;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatentsAndTestCenterPage extends ContentPage {

    public PatentsAndTestCenterPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Tradition of Innovation",
            "Patents and R&D Test Center"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }

}
