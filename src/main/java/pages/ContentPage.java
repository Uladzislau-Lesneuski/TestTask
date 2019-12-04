package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class ContentPage extends BasePage {
    public ContentPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$C006$ctl00$ctl00$textBox_write']")
    WebElement nameField;

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$C008$ctl00$ctl00$textBox_write']")
    WebElement companyField;

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$C010$ctl00$ctl00$textBox_write']")
    WebElement emailField;

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$C011$ctl00$ctl00$textBox_write']")
    WebElement phoneField;

    @FindBy (xpath = "//textarea[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$ctl09$C009$ctl00$ctl00$textBox_write']")
    WebElement commentsField;

    @FindBy (xpath = "//h2[text()='How can we help?']")
    WebElement titleOfForm;

    @FindBy (xpath = "//select[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$ctl02$C015$ctl00$ctl00$dropDown']")
    WebElement choiceDropDownn;

    @FindBy (xpath = "//select[@name='ctl00$ContentPlaceHolderFooter$TADBF704B033$ctl00$ctl00$ctl02$C019$ctl00$ctl00$dropDownCountry']")
    WebElement countryDropdown;

    @FindBy (xpath = "//input[@value='Submit']")
    WebElement submitButton;

    @FindBy (id = "ContentPlaceHolderFooter_TADBF704B033_ctl00_ctl00_successMessage")
    WebElement successMessage;

    public void scrollToSendMoreInformationForm() {
        wait.until(ExpectedConditions.visibilityOf(titleOfForm));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titleOfForm);
    }

    public void fillFormWithRandomData() {
        Select choice, country;
        int choiceIndex, countryIndex;
        List<WebElement> choiceSize, countrySize;
        Random random = new Random();

        choice = new Select(choiceDropDownn);
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
}


