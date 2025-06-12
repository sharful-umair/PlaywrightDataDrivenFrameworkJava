package org.sharfulumair.rough;

import com.microsoft.playwright.Browser;
import org.sharfulumair.base.BaseTest;
import org.testng.annotations.Test;

public class GmailLoginTest extends BaseTest {

    @Test
    public void doGmailLogin()
    {
        Browser browser = getBrowser("firefox");
        navigate(browser, "https://mail.google.com/");
        type("username", "Playwright");
    }
}
