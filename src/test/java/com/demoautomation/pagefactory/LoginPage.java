package com.demoautomation.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = "#username")
    private WebElement userNameInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@id = 'signin-button']")
    private WebElement loginButton;

    @FindBy(css="#message")
    private WebElement errorMessage;

    public LoginPage (WebDriver driver) {
        super(driver);
    }

    public void fillUserInput(String userName) {
        this.userNameInput.sendKeys(userName);
    }

    public void fillPasswordInput(String password) {
        this.passwordInput.sendKeys(password);
    }

    public void clickLoginButton () {
        this.loginButton.click();
    }

    public boolean isErrorMessageCorrect(String expectedMessage) {
        super.waitFor(ExpectedConditions.textToBePresentInElement(this.errorMessage, expectedMessage));
        String currentMessage = this.errorMessage.getText();
        return currentMessage.equalsIgnoreCase(expectedMessage);
    }
}
