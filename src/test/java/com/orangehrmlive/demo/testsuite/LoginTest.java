package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import com.orangehrmlive.demo.resources.testdata.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    DashboardPage dashboardPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        dashboardPage = new DashboardPage();

    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldLoginSuccessFully() {
        loginPage.loginToApp("Admin", "admin123");          //login
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatTheLogoDisplayOnHomePage() {
        loginPage.loginToApp("Admin", "admin123");          //login
        Assert.assertEquals(homePage.isLogoDisplayed(), true);        //verify logo is displayed
    }

    @Test(groups = {"regression"})
    public void verifyUserShouldLogOutSuccessFully() {
        loginPage.loginToApp("Admin", "admin123");          //login
        dashboardPage.clickOnUserProfileLogo();
        dashboardPage.clickOnLogoutButton();
    }

    @Test(groups = {"regression"}, dataProvider = "loginData", dataProviderClass = TestData.class)
    public void verifyErrorMessageWithInvalidCredentials(String username, String pwd, String errorMsg) {
        loginPage.loginToApp(username,pwd);          //login
        Assert.assertEquals(loginPage.getLoginErrorMsg(), errorMsg);
    }



}