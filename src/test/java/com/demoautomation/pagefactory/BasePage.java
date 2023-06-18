package com.demoautomation.pagefactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class BasePage {

    @FindBy (xpath = "//a[text() = 'Home']")
    private WebElement homeButton;

    @FindBy (xpath = "//a[text() = 'Form']")
    private WebElement formButton;

    @FindBy (xpath = "//a[text() = 'Grid']")
    private WebElement gridButton;

    @FindBy (xpath = "//a[text() = 'Search']")
    private WebElement searchButton;

    protected final WebDriver driver;
    protected final FluentWait<WebDriver> wait;

    public BasePage(WebDriver driver){
        this.driver  = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        PageFactory.initElements(this.driver,this);
    }

    protected void waitFor(ExpectedCondition<?> waitCondition) {
        wait.until(waitCondition);
    }

    protected void explicitWaitForVisibilityElement(WebElement element) {
        waitFor(ExpectedConditions.visibilityOf(element));
    }

    public void clickHomeButton () {
        this.homeButton.click();
    }

    public void clickFormButton () { this.formButton.click(); }

    public void clickGridButton () {
        this.gridButton.click();
    }

    public void clickSearchButton () {
        this.searchButton.click();
    }

}
