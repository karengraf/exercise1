package com.demoautomation.tests;

import com.demoautomation.pagefactory.ConfirmationPage;
import com.demoautomation.pagefactory.FormPage;
import com.demoautomation.util.LoadDataProviderFromFile;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;

public class FormTest extends BaseTest {

    private FormPage formPage;
    private ConfirmationPage confirmationPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.formPage = new FormPage(driver);
        this.confirmationPage = new ConfirmationPage(driver);
    }


    @Test(priority = 1, dataProvider = "fillFormDataProvider", dataProviderClass = LoadDataProviderFromFile.class)
    public void SuccessfullyFillForm(Map<String,String> testData){
        this.formPage.fillFirstNameInput(testData.get("name"));
        this.formPage.fillEmailInput(testData.get("email"));
        this.formPage.fillAddressInput(testData.get("address"));
        this.formPage.fillCityInput(testData.get("city"));
        this.formPage.fillStateInput(testData.get("state"));
        this.formPage.fillZipInput(testData.get("zip"));
        this.formPage.fillCardNameInput(testData.get("cardName"));
        this.formPage.fillCardNumberInput(testData.get("cardNumber"));
        this.formPage.fillMonthSelect(testData.get("month"));
        this.formPage.fillYearInput(testData.get("year"));
        this.formPage.fillCvvInput(testData.get("cvv"));
        this.formPage.shouldCheckBox(true);
        this.formPage.clickSubmitButton();
        this.sleepFor(3000);
        Assert.assertTrue(this.confirmationPage.isOrderConfirmed());
        Assert.assertNotNull(this.confirmationPage.getOrderNumber());
    }

    @Test(priority = 2, dataProvider = "fillFormDataProvider", dataProviderClass = LoadDataProviderFromFile.class)
    public void TryToGetCheckoutAlert(Map<String,String> testData) {
        this.formPage.fillFirstNameInput(testData.get("name"));
        this.formPage.fillEmailInput(testData.get("email"));
        this.formPage.fillAddressInput(testData.get("address"));
        this.formPage.fillCityInput(testData.get("city"));
        this.formPage.fillStateInput(testData.get("state"));
        this.formPage.fillZipInput(testData.get("zip"));
        this.formPage.fillCardNameInput(testData.get("cardName"));
        this.formPage.fillCardNumberInput(testData.get("cardNumber"));
        this.formPage.fillMonthSelect(testData.get("month"));
        this.formPage.fillYearInput(testData.get("year"));
        this.formPage.fillCvvInput(testData.get("cvv"));
        this.formPage.shouldCheckBox(false);
        this.formPage.clickSubmitButton();
        this.sleepFor(3000);
        Assert.assertTrue(this.formPage.isAlertShown());
    }

    @Test(priority = 3)
    public void IsTotalCartCorrect() {
        Assert.assertTrue(this.formPage.isTotalCorrect(), "Error: cart total not correct!");
    }
}
