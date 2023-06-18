package com.demoautomation.tests;

import com.demoautomation.pagefactory.HomePage;
import com.demoautomation.pagefactory.LoginPage;
import com.demoautomation.pagefactory.SearchPage;
import com.demoautomation.util.LoadDataProviderFromFile;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchTest extends BaseTest {
    private SearchPage searchPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.searchPage = new SearchPage(driver);
    }


    @Test(priority = 1, dataProvider = "fillSearchBar", dataProviderClass = LoadDataProviderFromFile.class)
    public void TryToSearchForWord(Map<String,String> testData) {
        String searchWord = testData.get("searchWord");
        this.searchPage.fillSearchBoxInput(searchWord);
        this.searchPage.clickSearchButton();
        Assert.assertTrue(this.searchPage.isResultMessageCorrect("Found one result for "+searchWord+""));
    }

    @Test(priority = 2)
    public void TryToSearchBlankSpace() {
        this.searchPage.fillSearchBoxInput("");
        this.searchPage.clickSearchButton();
        Assert.assertTrue(this.searchPage.isResultMessageCorrect("Please provide a search word."));
    }
}
