import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import webdriversingleton.WebDriverSingleton;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestTaskTests {
    WebDriver driver;
    BasePage basePage;
    ContentPage contentPage;
    FindDealerPage findDealerPage;
    OurLeadershipPage leadershipPage;
    FuelEconomyPage fuelEconomyPage;
    PatentsAndTestCenterPage patentsAndTestCenterPage;
    ProductPortfolioPage productPortfolioPage;
    TestPage testPage;
    CorrosivesPage corrosivesPage;
    BenefitsPage benefitsPage;

    @BeforeMethod
    @Parameters({"browserName", "isHeadless"})
    public void start() {
        driver = WebDriverSingleton.create();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("http://qa.yotec.net/");
    }

    @AfterMethod
    public void finish() {
        WebDriverSingleton.kill();
    }

    @Test
    public void navigateThroughSeveralMainMenuItems() {
        basePage = new BasePage();
        contentPage = new ContentPage();

        basePage.goToOurLeadershipPage();
        Assert.assertEquals(leadershipPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                leadershipPage.getBreadCrumbs()), "Orders and names of breadcrumbs should be equal");
        driver.navigate().back();

        basePage.goToFuelEconomyPage();
        Assert.assertEquals(fuelEconomyPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                fuelEconomyPage.getBreadCrumbs()), "Orders and names of breadcrumbs should be equal");
        driver.navigate().back();

        basePage.goToPatentsAndTestCenterPage();
        Assert.assertEquals(patentsAndTestCenterPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                patentsAndTestCenterPage.getBreadCrumbs()), "Orders and names of breadcrumbs should be equal");
        driver.navigate().back();

        basePage.goToCorrosivesPage();
        Assert.assertEquals(corrosivesPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                corrosivesPage.getBreadCrumbs()), "Orders and names of breadcrumbs should be equal");
        driver.navigate().back();

        basePage.goToProductPortfolioPage();
        Assert.assertEquals(productPortfolioPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                productPortfolioPage.getBreadCrumbs()), "Orders and names of breadcrumbs should be equal");
        driver.navigate().back();

        basePage.goToBenefitsPage();
        Assert.assertEquals(benefitsPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                benefitsPage.getBreadCrumbs()), "Orders and names of breadcrumbs should be equal");
        driver.navigate().back();

        basePage.goToTestPage();
        Assert.assertNotEquals(testPage.getExpectedOrder(), contentPage.getBreadcrumbsOrder(
                testPage.getBreadCrumbs()), "Bread crumbs contain different headers");
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

        basePage.goToContentPage();
        contentPage.scrollToSendMoreInformationForm();
        contentPage.fillFormWithRandomData();
        contentPage.submitForm();

        Assert.assertTrue(contentPage.isContactUsFormSendSuccessfully(),
                "Contact us page should sends successfully");
    }

    @Test (groups = "location")
    public void locationSearchHasRightResult() {
        basePage = new BasePage();

        basePage.goToDealerPage();
        String searchQuery = "truck";
        findDealerPage.enterSearchString(searchQuery);

        Assert.assertTrue(findDealerPage.isResultsContainEnteredString(searchQuery),
                "Result of search should be correct");
        Assert.assertTrue(findDealerPage.isSearchWithRandomDataGetEmptyResult(),
                "Search with random data should return empty result");
    }

    @Test (groups = "location")
    public void differentQuantityInSearchResultWhenChangingRadius() {
        basePage = new BasePage();

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
