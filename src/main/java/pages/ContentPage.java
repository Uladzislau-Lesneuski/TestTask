package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContentPage extends BasePage {
    public ContentPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//input[@placeholder='Name']")
    WebElement nameField;

    @FindBy (xpath = "//input[@placeholder='Company']")
    WebElement companyField;

    @FindBy (xpath = "//input[@placeholder='Email']")
    WebElement emailField;

    @FindBy (xpath = "//input[@placeholder='Phone']")
    WebElement phoneField;

    @FindBy (xpath = "//textarea[@placeholder='Comments']")
    WebElement commentsField;

    @FindBy (xpath = "//h2[text()='How can we help?']")
    WebElement titleOfForm;

    @FindBy (xpath = "//span/select[contains(@name,'dropDown')]")
    WebElement choiceDropDown;

    @FindBy (xpath = "//select[contains(@name, 'dropDownCountry')]")
    WebElement countryDropdown;

    @FindBy (xpath = "//input[@value='Submit']")
    WebElement submitButton;

    @FindBy (xpath = "//div[contains(@id, 'successMessage')]")
    WebElement successMessage;

    @FindAll({
            @FindBy(xpath = "//div[contains(@id, 'Breadcrumb')]//a")
    })
    List<WebElement> breadCrumbs;

    public List<WebElement> getBreadCrumbs() {
        return breadCrumbs;
    }

    public void scrollToSendMoreInformationForm() {
        wait.until(ExpectedConditions.visibilityOf(titleOfForm));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titleOfForm);
    }

    public void fillFormWithRandomData() {
        Select choice, country;
        int choiceIndex, countryIndex;
        List<WebElement> choiceSize, countrySize;
        Random random = new Random();

        choice = new Select(choiceDropDown);
        choiceSize = choice.getOptions();
        choiceIndex = random.nextInt(choiceSize.size());
        choice.selectByIndex(choiceIndex);

        country = new Select(countryDropdown);
        countrySize = country.getOptions();
        countryIndex = random.nextInt(countrySize.size());
        country.selectByIndex(countryIndex);

        nameField.sendKeys(generateRandomString(7));
        companyField.sendKeys(generateRandomString(7));
        emailField.sendKeys(generateRandomString(7));
        phoneField.sendKeys(generateRandomString(7));
        commentsField.sendKeys(generateRandomString(25));

    }

    public void submitForm() {
        submitButton.click();
    }

    public boolean isContactUsFormSendSuccessfully() {
        return successMessage.getText().contains("Success! Thanks for filling out our form!");
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


