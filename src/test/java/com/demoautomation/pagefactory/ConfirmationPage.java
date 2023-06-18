package com.demoautomation.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationPage extends BasePage {
    @FindBy(css = "[data-id=ordernumber]")
    private WebElement orderNumber;

    public ConfirmationPage (WebDriver driver) {
        super(driver);
    }

    public boolean isOrderConfirmed() { return this.orderNumber.isDisplayed(); }

    public String getOrderNumber() {
        String message = this.orderNumber.getText();
        return message.split(":")[1];
    }

}
