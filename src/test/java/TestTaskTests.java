import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactPage;
import webdriversingleton.WebDriverSingleton;

import java.util.concurrent.TimeUnit;

public class TestTaskTests {
    WebDriver driver;
    BasePage basePage;
    ContactPage contactPage;

    @BeforeMethod
    @Parameters({"browserName", "isHeadless"})
    public void start() {
        driver = WebDriverSingleton.create();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        driver.get("http://qa.yotec.net/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void finish() {
        WebDriverSingleton.kill();
    }

    @Test (enabled = false)
    public void navigateThroughMainMenuItems() {
        basePage = new BasePage();

        basePage.navigateThroughRandomItemsFromEachSection();

        int d = 0;

    }

    @Test
    public void howCanWeHelpTest() {
        basePage = new BasePage();
        contactPage = new ContactPage();
        basePage.goToContactPage();
        contactPage.scrollToSendMoreInformationForm();
        contactPage.fillFormWithRandomData();
        contactPage.submitForm();
        Assert.assertTrue(contactPage.isContactUsFormSendSuccessfully(), "Contact us page should send successfully");

    }

}
