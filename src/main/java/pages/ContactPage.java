package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Random;

public class ContactPage extends BasePage {
    public ContactPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderContent$TC9310CFE024$ctl00$ctl00$C006$ctl00$ctl00$textBox_write']")
    WebElement inputField1;

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderContent$TC9310CFE024$ctl00$ctl00$C008$ctl00$ctl00$textBox_write']")
    WebElement inputField2;

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderContent$TC9310CFE024$ctl00$ctl00$C010$ctl00$ctl00$textBox_write']")
    WebElement inputField3;

    @FindBy (xpath = "//input[@name='ctl00$ContentPlaceHolderContent$TC9310CFE024$ctl00$ctl00$C011$ctl00$ctl00$textBox_write']")
    WebElement inputField4;

    @FindBy (xpath = "//textarea[@name='ctl00$ContentPlaceHolderContent$TC9310CFE024$ctl00$ctl00$ctl08$C009$ctl00$ctl00$textBox_write']")
    WebElement textArea;

    @FindBy (xpath = "//h2[text()='Send more information']")
    WebElement titleOfForm;

    @FindBy (xpath = "//input[@value='Submit']")
    WebElement submitButton;

    @FindBy (id = "ContentPlaceHolderContent_TC9310CFE024_ctl00_ctl00_successMessage")
    WebElement successMesssage;

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


    public void scrollToSendMoreInformationForm() {
        wait.until(ExpectedConditions.visibilityOf(titleOfForm));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titleOfForm);
    }


    public void fillFormWithRandomData() {
        inputField1.sendKeys(generateRandomString(7));
        inputField2.sendKeys(generateRandomString(7));
        inputField3.sendKeys(generateRandomString(7));
        inputField4.sendKeys(generateRandomString(7));
        textArea.sendKeys(generateRandomString(25));

    }

    public void submitForm() {
        submitButton.click();
    }

    public boolean isContactUsFormSendSuccessfully() {
        return successMesssage.getText().contains("Success! Thanks for filling out our form!");
    }
}


