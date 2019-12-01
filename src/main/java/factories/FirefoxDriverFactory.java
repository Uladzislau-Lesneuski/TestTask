package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver create() {
        return new FirefoxDriver();
    }
}
