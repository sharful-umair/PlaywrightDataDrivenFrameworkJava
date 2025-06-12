package org.sharfulumair.testcases;

import org.sharfulumair.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;


public class BankManagerLoginTest extends BaseTest {


    @Test
    public void loginAsBankManager() {

        Browser browser = getBrowser("chrome");
        navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
        click("bmlBtn_CSS");
        System.out.println(isElementPresent("addCustBtn_CSS"));
        Assert.assertTrue(isElementPresent("addCustBtn_CSS"),"Bank Manager not logged in");
    }
}
