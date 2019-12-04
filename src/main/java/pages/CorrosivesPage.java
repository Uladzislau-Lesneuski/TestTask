package pages;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorrosivesPage extends ContentPage {

    public CorrosivesPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Our Products", "Trailers",
            "Tank Trailers", "Stainless Steel", "Corrosives"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }
}
