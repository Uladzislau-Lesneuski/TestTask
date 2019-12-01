package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver create() {
        Boolean isIncognitoMode = System.getProperty("IncognitoMode", "false").equalsIgnoreCase("true");
        Boolean isHeadless = System.getProperty("headless", "false").equalsIgnoreCase("true");
        ChromeOptions chromeOptions = new ChromeOptions();

        if (isIncognitoMode) {
            chromeOptions.addArguments("--incognito");
            chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }

        if (isHeadless) {
            chromeOptions.addArguments("--headless");
            chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        return new ChromeDriver(chromeOptions);
    }
}
