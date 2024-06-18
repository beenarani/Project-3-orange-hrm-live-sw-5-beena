package com.orangehrmlive.demo.resources.testdata;

import org.testng.annotations.DataProvider;

public class TestData {


    @DataProvider(name = "searchUserData")
    public Object[][] getBuildComData() {
        Object[][] data = new Object[][]{
                {"Aman Gupta", "Admin", "Test Tester", "Enabled"},
        };
        return data;
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        Object[][] data = new Object[][]{
                {"test123@gmail.com", "test123 ", "Invalid credentials"},
        };
        return data;
    }

}