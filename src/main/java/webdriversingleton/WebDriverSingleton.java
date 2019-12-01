package webdriversingleton;

import factories.ChromeDriverFactory;
import factories.FirefoxDriverFactory;
import factories.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    static WebDriverFactory factory;

    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private WebDriverSingleton() {
    }

    public static WebDriver create() {
        String browserName = System.getProperty("browserName", "chrome");

        if (browserName.equalsIgnoreCase("firefox")) {
            factory = new FirefoxDriverFactory();
        }
        else {
            factory = new ChromeDriverFactory();
        }

        if (driver.get() == null) {
            driver.set(factory.create());
        }

        return driver.get();
    }

    public static void kill() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        driver.set(null);
    }
}
