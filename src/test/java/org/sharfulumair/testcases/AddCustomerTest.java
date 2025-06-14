package org.sharfulumair.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import org.sharfulumair.base.BaseTest;
import org.sharfulumair.utilities.Constants;
import org.sharfulumair.utilities.DataProviders;
import org.sharfulumair.utilities.DataUtil;
import org.sharfulumair.utilities.ExcelReader;

public class AddCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")
    public void addCustomerTest(Hashtable<String,String> data) {
        
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"), excel);
        browser = getBrowser(data.get("browser"));
        navigate(browser, Constants.URL);
        click("bmlBtn_CSS");
        click("addCustBtn_CSS");
        type("firstname_CSS", data.get("firstname"));
        type("lastname_CSS", data.get("lastname"));
        type("postcode_CSS", data.get("postcode"));
        click("addbtn_CSS");
    }
}
