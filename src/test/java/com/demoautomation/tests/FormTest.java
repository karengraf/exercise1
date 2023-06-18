package com.demoautomation.tests;

import com.demoautomation.pagefactory.ConfirmationPage;
import com.demoautomation.pagefactory.FormPage;
import com.demoautomation.pagefactory.SearchPage;
import com.demoautomation.util.LoadDataProviderFromFile;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FormTest extends BaseTest {

    private FormPage formPage;
    private ConfirmationPage confirmationPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.formPage = new FormPage(driver);
        this.confirmationPage = new ConfirmationPage(driver);
    }

    /*@DataProvider(name="fillFormDataProvider")
    public Object[][] fillFormDataProvider() {
        Map<String,String> record = new HashMap<>();
        record.put("name","Lisa");
        record.put("email","lisamayer@gmail.com");
        record.put("address", "Calle Alcalá 21");
        record.put("city", "Madrid");
        record.put("state", "Madrid");
        record.put("zip", "28003");
        record.put("card name", "Lisa Mayer");
        record.put("card number", "4513 2568 5876 0869");
        record.put("month", "September");
        record.put("year", "2022");
        record.put("cvv", "593");


        Map<String,String> record2 = new HashMap<>();
        record2.put("name","Stefan");
        record2.put("email","stefanmartinez@gmail.com");
        record2.put("address", "Avenida del Oeste 23");
        record2.put("city", "Valencia");
        record2.put("state", "Comunidad Valenciana");
        record2.put("zip", "46001");
        record2.put("card name", "Stefan Martinez");
        record2.put("card number", "5191 7323 4397 5393");
        record2.put("month", "April");
        record2.put("year", "2023");
        record2.put("cvv", "867");

        return new Object[][] {
                {record},
                {record2}
        };
    }*/

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
