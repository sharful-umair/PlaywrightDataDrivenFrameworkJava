package org.sharfulumair.testcases;

import java.util.Hashtable;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import org.sharfulumair.base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.sharfulumair.utilities.Constants;
import org.sharfulumair.utilities.DataProviders;
import org.sharfulumair.utilities.DataUtil;
import org.sharfulumair.utilities.ExcelReader;

public class OpenAccountTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")
    public void openAccountTest(Hashtable<String,String> data) {

        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "OpenAccountTest", data.get("Runmode"), excel);
        browser = getBrowser(data.get("browser"));
        navigate(browser, Constants.URL);
        click("bmlBtn_CSS");
        click("openaccount_CSS");
        select("customer_CSS",data.get("customer"));
        select("currency_CSS",data.get("currency"));
        click("process_CSS");
    }
}
