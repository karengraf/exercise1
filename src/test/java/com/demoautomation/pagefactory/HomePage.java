package com.demoautomation.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn (String userName) {
        return driver.findElement(By.xpath("//p[text()='"+userName+"']")).isDisplayed();
    }
}
