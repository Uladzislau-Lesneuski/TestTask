package pages;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuelEconomyPage extends ContentPage {

    public FuelEconomyPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "We Are Wabash",
            "Corporate Responsibility", "Sustainability", "Fuel Economy"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }
}
