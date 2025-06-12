package org.sharfulumair.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.SelectOption;

import org.sharfulumair.extentlisteners.ExtentListeners;

import static org.apache.commons.io.FileUtils.waitFor;

public class BaseTest {

    private Playwright playwright;
    public Browser browser;
    public Page page;
    private static Properties OR = new Properties();
    private static FileInputStream fis;
    private Logger log = Logger.getLogger(this.getClass());

    private static ThreadLocal<Playwright> pw = new ThreadLocal<>();
    private static ThreadLocal<Browser> br = new ThreadLocal<>();
    private static ThreadLocal<Page> pg = new ThreadLocal<>();

    public static Playwright getPlaywright() {

        return pw.get();
    }

    public static Browser getBrowser() {

        return br.get();
    }

    public static Page getPage() {

        return pg.get();
    }

    @BeforeSuite
    public void setUp() {

        PropertyConfigurator.configure("./src/resources/properties/log4j.properties");
        log.info("Test Execution started !!!");

        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/resources/properties/OR.properties");
            //fis = new FileInputStream("./src/resources/properties/OR.properties");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            OR.load(fis);
            log.info("OR Properties file loaded.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void click(String locatorKey) {

        try {
            String selector = OR.getProperty(locatorKey);
            if (selector == null) {
                log.error("Locator not found in OR.properties: " + locatorKey);
                Assert.fail("Locator not found in OR.properties: " + locatorKey);
            }
            getPage().locator(selector).click();
            log.info("Clicking on an Element : " + locatorKey + " with selector: " + selector);
            ExtentListeners.getExtent().info("Clicking on an Element : " + locatorKey);
        } catch (Throwable t) {
            log.error("Error while clicking on an Element : " + t.getMessage());
            ExtentListeners.getExtent()
                    .fail("Clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    public boolean isElementPresent(String locatorKey) {
        try {
            String selector = OR.getProperty(locatorKey);
            if (selector == null) {
                log.error("Locator not found in OR.properties: " + locatorKey);
                Assert.fail("Locator not found in OR.properties: " + locatorKey);
            }

            log.info("Finding an Element: " + locatorKey);
            getPage().locator(selector).waitFor(new Locator.WaitForOptions().setTimeout(5000));
            ExtentListeners.getExtent().info("Finding an Element : " + locatorKey);
            return getPage().locator(selector).isVisible();

        } catch (Throwable t) {
            log.error("Error while locating the Element: " + t.getMessage(), t);
            ExtentListeners.getExtent().fail("Error while finding an Element : " + locatorKey);
            throw t; // Rethrow to preserve original error context.
        }
    }

    public void type(String locatorKey, String value) {
        try {
            String selector = OR.getProperty(locatorKey);
            if (selector == null) {
                log.error("Locator not found in OR.properties: " + locatorKey);
                Assert.fail("Locator not found in OR.properties: " + locatorKey);
            }
            getPage().locator(selector).fill(value);
            log.info("Typing in an Element : " + locatorKey + " with selector: " + selector + " and entered value: " + value);
            ExtentListeners.getExtent()
                    .info("Typing in an Element : " + locatorKey + " with selector: " + selector + " and entered value: " + value);
        } catch (Throwable t) {
            log.error("Error while typing in an Element : " + locatorKey + " - " + t.getMessage());
            ExtentListeners.getExtent().fail(
                    "Error while typing in an Element : " + locatorKey + " - " + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }



    public void select(String locatorKey, String value) {
        try {
            String selector = OR.getProperty(locatorKey);
            if (selector == null) {
                log.error("Locator not found in OR.properties: " + locatorKey);
                Assert.fail("Locator not found in OR.properties: " + locatorKey);
            }
            getPage().locator(selector).selectOption(new SelectOption().setLabel(value));
            log.info("Typing in an Element : " + locatorKey + " and entered the value as :" + value);
            ExtentListeners.getExtent()
                    .info("Selecting an Element : " + locatorKey + " and selected the value as :" + value);
        } catch (Throwable t) {
            log.error("Error while typing in an Element : " + t.getMessage());
            ExtentListeners.getExtent().
                    fail("Error while Selecting an Element : " + t.getMessage() + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    public Browser getBrowser(String browserName) {

        playwright = Playwright.create();
        pw.set(playwright);

        switch (browserName) {
            case "chrome":
                log.info("Launching Chrome browser");
                return getPlaywright().chromium()
                        .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
            case "headless":
                log.info("Launching Headless Mode");
                return getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            case "firefox":
                log.info("Launching Firefox browser");
                return getPlaywright().firefox()
                        .launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));
            case "webkit":
                log.info("Launching Webkit browser");
                return getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            default:
                throw new IllegalArgumentException();
        }
    }

    public void navigate(Browser browser, String url) {
        this.browser = browser;
        br.set(browser);
        page = getBrowser().newPage();
        pg.set(page);
        getPage().navigate(url);
        log.info("Navigated to : " + url);

        getPage().onDialog(dialog -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            dialog.accept();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(dialog.message());
        });
    }

    @AfterMethod
    public void quit() {

//        if (getPage() != null) {
//            getBrowser().close();
//            getPage().close();
//            getPlaywright().close();
//        }

        try {
            if (getPage() != null) {
                getPage().close();
            }
            if (getBrowser() != null) {
                getBrowser().close();
            }
            if (getPlaywright() != null) {
                getPlaywright().close();
            }

            // Clean up ThreadLocal references
            pg.remove();
            br.remove();
            pw.remove();

        } catch (Exception e) {
            log.error("Error during teardown: " + e.getMessage(), e);
        }
    }

//    @AfterSuite
//    public void quitPlaywright() {
//        if (getPage() != null) {
//            getPlaywright().close();
//        }
//    }
}