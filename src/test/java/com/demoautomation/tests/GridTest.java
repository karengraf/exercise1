package com.demoautomation.tests;


import com.demoautomation.pagefactory.GridPage;
import com.demoautomation.pagefactory.HomePage;
import com.demoautomation.pagefactory.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GridTest extends BaseTest {
    private GridPage gridPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.gridPage = new GridPage(driver);
    }

    @Test(priority = 1)
    @Parameters({"itemIndex"})
    public void CheckPod7PriceAndName(int itemIndex) {
        Assert.assertEquals(this.gridPage.getItemNameAtPosition(itemIndex), "Super Pepperoni");
        Assert.assertEquals(this.gridPage.getItemPriceAtPosition(itemIndex), "$10");
    }

    @Test(priority = 2)
    public void CheckAllItems() {
        Assert.assertTrue(this.gridPage.checkAllTitlesAreShown());
        Assert.assertTrue(this.gridPage.checkAllPricesAreShown());
        Assert.assertTrue(this.gridPage.checkAllImagesAreShown());
        Assert.assertTrue(this.gridPage.checkAllButtonsAreShown());
    }


}
