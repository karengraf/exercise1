package com.demoautomation.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

    @FindBy(css="[placeholder=\"Search..\"]")
    private WebElement searchBoxInput;

    @FindBy(css="[type=\"submit\"]")
    private WebElement searchButton;

    @FindBy(css="#result")
    private WebElement resultMessage;

    public SearchPage (WebDriver driver) {
        super(driver);
    }

    public void fillSearchBoxInput(String searchWord) {
        this.searchBoxInput.sendKeys(searchWord);
    }

    public void clickSearchButton () {
        this.searchButton.click();
    }

    public boolean isResultMessageCorrect (String expectedMessage) {
        super.waitFor(ExpectedConditions.textToBePresentInElement(this.resultMessage, expectedMessage));
        String currentMessage = this.resultMessage.getText();
        return currentMessage.equalsIgnoreCase(expectedMessage);
    }
}
