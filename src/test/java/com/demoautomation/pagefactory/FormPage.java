package com.demoautomation.pagefactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FormPage extends BasePage {
    @FindBy(css = "[name=firstname]")
    private WebElement firstNameInput;

    @FindBy(css = "[name=email]")
    private WebElement emailInput;

    @FindBy(css = "[name=address]")
    private WebElement addressInput;

    @FindBy(css = "[name=city]")
    private WebElement cityInput;

    @FindBy(css = "[name=state]")
    private WebElement stateInput;

    @FindBy(css = "[name=zip]")
    private WebElement zipInput;

    @FindBy(css = "[name=cardname]")
    private WebElement cardNameInput;

    @FindBy(css = "[name=cardnumber]")
    private WebElement cardNumberInput;

    @FindBy(css = "[name=expmonth]")
    private WebElement monthInput;

    @FindBy(css = "[name=expyear]")
    private WebElement yearInput;

    @FindBy (css = "[name=cvv]")
    private WebElement cvvInput;

    @FindBy(css = "[name=sameadr]")
    private WebElement checkBox;

    @FindBy(css = "button.btn")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(), \"Product\")]/following-sibling::span")
    private List<WebElement> prices;

    @FindBy(xpath = "//*[text()=\"Total \"]//b")
    private WebElement total;


    public FormPage (WebDriver driver) {
        super(driver);
    }

    public void fillFirstNameInput(String firstName) {
        this.firstNameInput.sendKeys(firstName);
    }

    public void fillEmailInput(String email) {
        this.emailInput.sendKeys(email);
    }

    public void fillAddressInput(String address) { this.addressInput.sendKeys(address); }

    public void fillCityInput(String city) {
        this.cityInput.sendKeys(city);
    }

    public void fillStateInput(String state) {
        this.stateInput.sendKeys(state);
    }

    public void fillZipInput(String zip) {
        this.zipInput.sendKeys(zip);
    }

    public void fillCardNameInput(String cardName) {
        this.cardNameInput.sendKeys(cardName);
    }

    public void fillCardNumberInput(String cardNumber) {
        this.cardNumberInput.sendKeys(cardNumber);
    }

    public void fillMonthSelect(String month) {
        Select monthSelect = new Select(this.monthInput);
        monthSelect.selectByVisibleText(month);
    }

    public void fillYearInput(String year) {
        this.yearInput.sendKeys(year);
    }

    public void fillCvvInput(String cvv) {
        this.cvvInput.sendKeys(cvv);
    }

    public void shouldCheckBox(boolean check) {
        if (check) {
            if (!this.checkBox.isSelected()) {
                this.checkBox.click();
            }
        }
        else {
            if (this.checkBox.isSelected()) {
                this.checkBox.click();
            }
        }
    }

    public void clickSubmitButton () {
        this.submitButton.click();
    }

    public boolean isAlertShown () {
        super.waitFor(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String currentText = alert.getText();
        boolean isCorrect = false;
        String expectedText = "Shipping address same as billing checkbox must be selected.";
        isCorrect = currentText.equalsIgnoreCase(expectedText);
        this.closeAlert();
        return isCorrect;
    }

    public void closeAlert() {
        super.waitFor(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isTotalCorrect() {

        boolean isSumCorrect = false;
        int sum = 0;

        for (WebElement price : prices) {
            String currentPrice = price.getText().split("\\$")[1];
            sum += Integer.parseInt(currentPrice);
        }
        int totalPrice = Integer.parseInt(this.total.getText().split("\\$")[1]);
        if (sum == totalPrice) {
            isSumCorrect = true;
        }
        return isSumCorrect;
    }




}
