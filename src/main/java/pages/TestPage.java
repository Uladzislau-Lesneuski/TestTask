package pages;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPage extends ContentPage {

    public TestPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Location Search", "Test"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }
}
