package org.sharfulumair.rough;

import com.microsoft.playwright.Browser;
import org.sharfulumair.base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void doLogin()
    {
        Browser browser = getBrowser("chrome");
        navigate(browser, "https://www.google.com/");
        type("searchBox", "Playwright");
    }
}
