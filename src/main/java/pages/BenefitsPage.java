package pages;

import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BenefitsPage extends ContentPage {

    public BenefitsPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Work With Wabash", "Benefits"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }
}
