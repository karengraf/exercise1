package com.demoautomation.tests;

import com.demoautomation.pagefactory.HomePage;
import com.demoautomation.pagefactory.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
    }


    @Test(priority = 1)
    @Parameters({"userName", "password"})
    public void TryToLoginWithValidCredentials(String userName, String password) {
        this.loginPage.fillUserInput(userName);
        this.loginPage.fillPasswordInput(password);
        this.loginPage.clickLoginButton();
        Assert.assertTrue(this.homePage.isLoggedIn(userName),"Error! Could not login with valid credentials.");
    }

    @Test(priority = 2)
    @Parameters({"userName"})
    public void TryToLoginWithInvalidCredentials(String userName) {
        this.loginPage.fillUserInput(userName);
        this.loginPage.fillPasswordInput("akdnaodw");
        this.loginPage.clickLoginButton();
        //Assert.assertEquals(this.loginPage.areCredentialsInvalidOrBlank(), "WRONG CREDENTIALS");
        //Assert.assertTrue(this.loginPage.areCredentialsInvalid());
        Assert.assertTrue(this.loginPage.isErrorMessageCorrect("WRONG CREDENTIALS"));
    }

    @Test(priority = 3)
    public void TryToLoginWithBlankCredentials() {
        this.loginPage.fillUserInput((""));
        this.loginPage.fillPasswordInput((""));
        this.loginPage.clickLoginButton();
        //Assert.assertEquals(this.loginPage.areCredentialsInvalidOrBlank(), "FIELDS CAN NOT BE EMPTY");
        //Assert.assertTrue(this.loginPage.areCredentialsBlank());
        Assert.assertTrue(this.loginPage.isErrorMessageCorrect("FIELDS CAN NOT BE EMPTY"));
    }
}
