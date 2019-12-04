package pages;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPortfolioPage extends ContentPage {

    public ProductPortfolioPage() {
        PageFactory.initElements(driver, this);
    }

    List<String> expectedBreadcrumbsHeaders = new ArrayList<>(Arrays.asList("Home", "Our Brands", "Benson",
            "Product Portfolio"));

    public List<String> getExpectedOrder() {
        return expectedBreadcrumbsHeaders;
    }
}
