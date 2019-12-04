import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContentPage;
import pages.FindDealerPage;
import webdriversingleton.WebDriverSingleton;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestTaskTests {
    WebDriver driver;
    BasePage basePage;
    ContentPage contentPage;
    FindDealerPage findDealerPage;

    @BeforeMethod
    @Parameters({"browserName", "isHeadless"})
    public void start() {
        driver = WebDriverSingleton.create();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

        driver.get("http://qa.yotec.net/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void finish() {
        WebDriverSingleton.kill();
    }

    @Test
    public void navigateThroughSeveralMainMenuItems() {
        basePage = new BasePage();

        basePage.goToOurLeadershipPage();

        //basePage.navigateThroughRandomItemsFromEachSection();

        int d = 0;

    }

    @Test
    public void checkResponseOfAllMenuLinks() {
        basePage = new BasePage();

        List<String> links = basePage.collectAllLinks();
        Assert.assertFalse(basePage.checkLinksResponse(links), "Main menu items contain broken links");
    }

    @Test
    public void howCanWeHelpTest() {
        basePage = new BasePage();
        contentPage = new ContentPage();

        basePage.goToContentPage();
        contentPage.scrollToSendMoreInformationForm();
        contentPage.fillFormWithRandomData();
        contentPage.submitForm();
        Assert.assertTrue(contentPage.isContactUsFormSendSuccessfully(), "Contact us page should sends successfully");
    }

    @Test (groups = "location")
    public void locationSearchRightResult() {
        basePage = new BasePage();
        findDealerPage = new FindDealerPage();

        basePage.goToDealerPage();
        findDealerPage.searchItem("truck");
        Assert.assertTrue(findDealerPage.isTruckItemDisplayed(), "Result of search should be correct");

    }

    @Test (groups = "location")
    public void differentQuantityInSearchResultWhenChangingRadius() {
        basePage = new BasePage();
        findDealerPage = new FindDealerPage();

        basePage.goToDealerPage();
        int initialQuantity = findDealerPage.getSearchItemsQuantity();

        findDealerPage.setSliderToMaxPosition();
        int maxQuantity = findDealerPage.getSearchItemsQuantity();
        Assert.assertNotEquals(initialQuantity, maxQuantity, "Quantity of search items should be different");

        findDealerPage.setSliderToDefaultPosition();
        int newQuantity = findDealerPage.getSearchItemsQuantity();
        Assert.assertEquals(initialQuantity, newQuantity, "Quantities should be equal");
    }

    @Test (groups = "location")
    public void clearAllFiltersVerification() {
        basePage = new BasePage();
        findDealerPage = new FindDealerPage();

        basePage.goToDealerPage();
        findDealerPage.fillFieldsWithRandomData();
        findDealerPage.checkAllCheckboxes();
        findDealerPage.setSliderToMaxPosition();
        findDealerPage.clearFilters();

        Assert.assertTrue(findDealerPage.isEachCheckboxUnchecked(), "All checkboxes should be unchecked");
        Assert.assertTrue(findDealerPage.isEachSearchFieldEmpty(), "Search fields should be empty");
        Assert.assertFalse(findDealerPage.isSliderInDefaultPosition(), "Slider is not in default position");
    }

}
