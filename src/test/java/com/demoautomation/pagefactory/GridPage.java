package com.demoautomation.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GridPage extends BasePage {
    @FindBy(css = ".item")
    private List<WebElement> pods;

    public GridPage (WebDriver driver) {
        super(driver);
    }

    public String getItemNameAtPosition (int index) {
        WebElement myPod = this.pods.get(index);
        return myPod.findElement(By.cssSelector("[data-test-id=item-name]")).getText();
    }

    public String getItemPriceAtPosition (int index) {
        WebElement myPod = this.pods.get(index);
        return myPod.findElement(By.cssSelector("#item-price")).getText();
    }

    public String getItemImageAtPosition (int index) {
        WebElement myPod = this.pods.get(index);
        return myPod.findElement(By.cssSelector("img")).getAttribute("src");
    }

    public String getButtonAtPosition (int index) {
        WebElement myPod = this.pods.get(index);
        return myPod.findElement(By.cssSelector("button")).getText();
    }

    public boolean checkAllTitlesAreShown () {

        boolean isShown = true;

        for (int i = 0; i < pods.size(); i++) {

            String itemTitle = this.getItemNameAtPosition(i);

            if (itemTitle.isEmpty()) {
                System.out.println(itemTitle);
                isShown = false;
                break;
            }
        }
        return isShown;
    }

    public boolean checkAllPricesAreShown () {

        boolean isShown = true;

        for (int i = 0; i < pods.size(); i++) {
            String itemPrice = this.getItemPriceAtPosition(i);

            if (itemPrice.isEmpty()) {
                isShown = false;
                break;
            }
        }
        return isShown;
    }

    public boolean checkAllImagesAreShown () {

        boolean isShown = true;

        for (int i = 0; i < pods.size(); i++) {
            String itemImage = this.getItemImageAtPosition(i);

            if (!itemImage.contains("png")) {
                isShown = false;
                break;
            }
        }
        return isShown;
    }

    public boolean checkAllButtonsAreShown () {

        boolean isShown = true;

        for (int i = 0; i < pods.size(); i++) {
            String itemButton = this.getButtonAtPosition(i);

            if (!itemButton.contains("Add to Order")) {
                isShown = false;
                break;
            }
        }
        return isShown;
    }


}
