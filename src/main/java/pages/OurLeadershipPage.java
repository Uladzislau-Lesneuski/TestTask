package pages;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OurLeadershipPage extends ContentPage{

    public OurLeadershipPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "We Are Wabash", "Our Leadership"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }
}
