package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriversingleton.WebDriverSingleton;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BasePage {
    WebDriver driver = WebDriverSingleton.create();

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, 20);

    Actions builder = new Actions(driver);

    @FindBy (css = "li.dropdown a[href='/we-are-wabash']")
    WebElement weAreWabashSection;

    @FindBy (css = "li.dropdown a[href='/tradition-of-innovation']")
    WebElement traditionOfInnovationSection;

    @FindBy (css = "li.dropdown a[href='/our-products']")
    WebElement ourProductsSection;

    @FindBy (css = "li.dropdown a[href='/our-brands']")
    WebElement ourBrandsSection;

    @FindBy (css = "li.dropdown a[href='/our-brands/benson']")
    WebElement bensonLink;

    @FindBy (css = "li.dropdown a[href='/our-brands/benson/product-portfolio']")
    WebElement productPortfolioLink;

    @FindBy (css = "li.dropdown a[href='/work-with-wabash']")
    WebElement workWithWabashSection;

    @FindBy (css = "li a[href='/timeline']")
    WebElement timeLineSection;

    @FindBy (css = "li.dropdown a[href='/location-search']")
    WebElement locationSearchSection;

    @FindAll({
            @FindBy (xpath = "//ul[contains(@class, 'CustomMenu')]//li/a")
    })
    List<WebElement> allMenuLinks;

    @FindBy (xpath = "//ul[contains(@class, 'CustomMenu')]//a[@href='/we-are-wabash/our-leadership']")
    WebElement ourLeadershipLink;

    @FindBy (xpath = "//ul[contains(@class, 'CustomMenu')]//a[@href='/we-are-wabash/corporate-responsibility']")
    WebElement corporateResponsibilityLink;

    @FindBy (css = "li.dropdown a[href='/we-are-wabash/corporate-responsibility/sustainability/fuel-economy']")
    WebElement fuelEconomyLink;

    @FindBy (css = "li.dropdown a[href='/tradition-of-innovation/patents-and-r-d-test-center']")
    WebElement patentsAndTestCenterLink;

    @FindBy (css = "li.dropdown a[href='/our-products/trailers/tank-trailers']")
    WebElement tankTrailersLink;

    @FindBy (css = "li.dropdown a[href='/our-products/trailers/tank-trailers/stainless-steel/corrosives']")
    WebElement corrosivesLink;

    @FindBy (css = "li.dropdown a[href='/work-with-wabash/benefits']")
    WebElement benefitsLink;

    @FindBy (css = "li.dropdown a[href='/location-search/test']")
    WebElement testLink;

    @FindAll({
            @FindBy (css = "ul[id*='childNodesContainer'] a")
            //@FindBy (xpath = "//ul[contains(@id, 'childNodesContainer')]//li")
    })
    List<WebElement> dropDownItems;

    @FindBy (css = "a[href='/location-search/dealer']")
    WebElement dealerLink;

    @FindAll({
            @FindBy(xpath = "//li[@class='rsmItem sfBreadcrumbNavigation']/a")
    })
    List<WebElement> breadCrumbs;

    public static String generateRandomString(int length) {
        Random random = new Random();
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = lower.toUpperCase();
        String number = "0123456789";
        String finalString = lower + upper + number;
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(finalString.length());
            char rndChar = finalString.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    public List<String> collectAllLinks(){
        List<String> links = new ArrayList<>();
        for (WebElement items: allMenuLinks) {
            builder.moveToElement(items).perform();
            links.add(items.getAttribute("href"));
        }

        return links;
    }


    public boolean checkLinksResponse(List<String> links) {
        boolean flag = false;
        HttpURLConnection httpURLConnection;
        int responseCode;

        for (String url: links) {
            try {
                httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
                httpURLConnection.connect();

                responseCode = httpURLConnection.getResponseCode();

                httpURLConnection.disconnect();

                if(responseCode >= 400){
                    System.out.println(url + " Link is broken");
                    flag = true;
                }
                else{
                    System.out.println(url + " Link is valid");
                    flag = false;
                }
            }
            catch(IOException exp) {
                exp.printStackTrace();
            }
        }
        return flag;
    }

    public void navigateThroughRandomItemsFromEachSection() {
        Random random = new Random();
        List<WebElement> subCategory;
        int count = 0;


        for (WebElement sections: allMenuLinks) {

            System.out.println("Section name: " + sections.getText() + "->");
            builder.moveToElement(sections).perform();

            subCategory = sections.findElements(By.cssSelector("ul[id*='childNodesContainer'] a"));
            //childItems = sections.findElements(By.xpath("//ul[contains(@id, 'childNodesContainer')]//a"));

            for (int i = 0; i < subCategory.size(); i++) {
                builder.moveToElement(subCategory.get(i)).perform();
                System.out.print("***" + subCategory.get(i).getText() + " : ");
                System.out.println();
                count++;
            }

            int range = count;

//            int index = random.nextInt(range - 0) + 0;
//
//            childItems.get(index).click();
//            driver.navigate().back();

            System.out.println("Number of subelements is " + range);

            System.out.println();
            count = 0;

        }
        System.out.println();
    }

    public void goToContentPage() {
        weAreWabashSection.click();
    }

    public void goToDealerPage() {
        builder.moveToElement(locationSearchSection).click(dealerLink).perform();
    }

    public void goToOurLeadershipPage() {
        wait.until(ExpectedConditions.visibilityOf(weAreWabashSection));
        builder.moveToElement(weAreWabashSection).click(ourLeadershipLink).perform();
    }

    public void goToFuelEconomyPage() {
        wait.until(ExpectedConditions.visibilityOf(weAreWabashSection));
        builder.moveToElement(weAreWabashSection).moveToElement(corporateResponsibilityLink).click(fuelEconomyLink).perform();
    }

    public void goToPatentsAndTestCenterPage() {
        wait.until(ExpectedConditions.visibilityOf(traditionOfInnovationSection));
        builder.moveToElement(traditionOfInnovationSection).click(patentsAndTestCenterLink).perform();
    }

    public void goToCorrosivesPage() {
        wait.until(ExpectedConditions.visibilityOf(ourProductsSection));
        builder.moveToElement(ourProductsSection).moveToElement(tankTrailersLink).click(corrosivesLink).perform();
    }

    public void goToBenefitsPage() {
        wait.until(ExpectedConditions.visibilityOf(workWithWabashSection));
        builder.moveToElement(workWithWabashSection).click(benefitsLink).perform();
    }

    public void goToTestPage() {
        wait.until(ExpectedConditions.visibilityOf(locationSearchSection));
        builder.moveToElement(locationSearchSection).click(testLink).perform();
    }

    public void goToProductPortfolioPage() {
        wait.until(ExpectedConditions.visibilityOf(ourBrandsSection));
        builder.moveToElement(ourBrandsSection).moveToElement(bensonLink).click(productPortfolioLink).perform();
    }

    public List<String> checkBreadcrumbsOrder(List<WebElement> breadcrumbs) {

        List<String> textFromBreadcrumbs = new ArrayList<>();

        for (WebElement text: breadcrumbs) {
            textFromBreadcrumbs.add(text.getText());
        }

        System.out.println(textFromBreadcrumbs);

        return textFromBreadcrumbs;
    }
}
