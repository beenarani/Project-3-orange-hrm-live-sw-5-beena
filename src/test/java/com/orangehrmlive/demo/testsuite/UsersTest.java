package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import com.orangehrmlive.demo.resources.testdata.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    AdminPage adminPage;
    AddUserPage addUserPage;
    ViewSystemUsersPage viewSystemUsersPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        adminPage = new AdminPage();
        addUserPage = new AddUserPage();
        viewSystemUsersPage = new ViewSystemUsersPage();

    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void adminShouldAddUserSuccessFully() throws InterruptedException {
        //login account
        loginPage.loginToApp("Admin","admin123");

        Thread.sleep(2000);
        //click on admin tab
        homePage.clickOnAdminTab();

        //verify text
        Assert.assertEquals(adminPage.getSystemUsersText(), "System Users");

        //click on add
        adminPage.clickOnAddButton();

        //verify add user
        Assert.assertEquals(addUserPage.getAddUserText(), "Add User");

        //expand dropdown
        addUserPage.clickOnRoleDropdown();

        //select admin user role
        addUserPage.selectAdminRoleFromDropdown();

        //enter user name
        addUserPage.enterUserName("Beena");

        // click on dropdown select status
        addUserPage.selectStatusDropdown();

        //select disable
        addUserPage.selectDisabledStatusFromDropdown();

        //enter pwd
        addUserPage.enterPassword("prime1234");

        //enter confirm pw
        addUserPage.enterConfirmPassword("prime1234");

        //click save
        addUserPage.clickOnSaveButton();

    }

    @Test(groups = {"smoke", "regression"})
    public void searchTheUserCreatedAndVerifyIt() throws InterruptedException {
        //login account
        loginPage.loginToApp("Admin","admin123");

        homePage.clickOnAdminTab();         //click on admin tab
        Assert.assertEquals(adminPage.getSystemUsersText(), "System Users");        //verify text
        //enter details and click on search
        viewSystemUsersPage.enterUsername("Admin");
        viewSystemUsersPage.clickOnRoleDropdown();
        viewSystemUsersPage.selectRoleFromDropdown("Admin");
        viewSystemUsersPage.selectStatusDropdown();
        viewSystemUsersPage.selectStatusFromDropdown("Enabled");
        viewSystemUsersPage.clickOnSearchButton();


    }




    @Test(groups = {"regression"})
    public void verifyThatAdminShouldDeleteTheUserSuccessFully() {
        //login account
        loginPage.loginToApp("Admin","admin123");

        homePage.clickOnAdminTab();         //click on admin tab
        Assert.assertEquals(adminPage.getSystemUsersText(), "System Users");        //verify text
        //enter details and click on search
        viewSystemUsersPage.enterUsername("ramanji");
        viewSystemUsersPage.clickOnRoleDropdown();
        viewSystemUsersPage.selectRoleFromDropdown("Admin");
        viewSystemUsersPage.selectStatusDropdown();
        viewSystemUsersPage.selectStatusFromDropdown("Enabled");
        viewSystemUsersPage.clickOnSearchButton();


        viewSystemUsersPage.clickOnSelectUserCheckbox();
        viewSystemUsersPage.clickOnDeleteUserButton();
        viewSystemUsersPage.clickOnYesDeleteButton();
        Assert.assertEquals(viewSystemUsersPage.getSuccessDeleteToasterMsg(), "Successfully Deleted");
    }

    @Test(groups = {"regression"}, dataProvider = "searchUserData", dataProviderClass = TestData.class)
    public void searchTheUserAndVerifyTheMessageRecordFound(String username, String role, String empName, String status) throws InterruptedException {
        loginPage.loginToApp("Admin","admin123");

        Thread.sleep(2000);
        homePage.clickOnAdminTab();         //click on admin tab
        Assert.assertEquals(adminPage.getSystemUsersText(), "System Users");        //verify text

        //enter details and click on search
        viewSystemUsersPage.enterUsername(username);

        //viewSystemUsersPage.enterEmpName(empName);
        viewSystemUsersPage.clickOnRoleDropdown();
        viewSystemUsersPage.selectRoleFromDropdown(role);
        viewSystemUsersPage.selectStatusDropdown();
        viewSystemUsersPage.selectStatusFromDropdown(status);


        Thread.sleep(2000);
        viewSystemUsersPage.clickOnSearchButton();

        Assert.assertEquals(viewSystemUsersPage.getRecordFoundText(), "(1) Record Found");
        Assert.assertEquals(viewSystemUsersPage.getUsernameText(), username);         //verify username
        viewSystemUsersPage.clickOnResetButton();           //reset all data
    }

}
