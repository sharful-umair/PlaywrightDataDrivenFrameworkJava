package org.sharfulumair.extentlisteners;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;
import org.sharfulumair.base.BaseTest;

public class ExtentManager {

    private static ExtentReports extent;
    public static String fileName;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Rahul Arora");
        extent.setSystemInfo("Organization", "Way2Automation");
        extent.setSystemInfo("Build no", "W2A-1234");

        return extent;
    }

    public static void captureScreenshot() throws IOException {

        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

        BaseTest.getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./reports/"+fileName)));
    }

    /*
     *
     * public static void captureScreenshot() throws IOException {
     *
     * Date d = new Date(); fileName = d.toString().replace(":", "_").replace(" ",
     * "_")+".jpg";
     *
     *
     *
     * File screeshot = ((TakesScreenshot)
     * BaseTest.driver).getScreenshotAs(OutputType.FILE);
     * FileUtils.copyFile(screeshot, new File(".//reports//"+fileName)); }
     *
     *
     *
     * public static void captureElementScreenshot(WebElement element) throws
     * IOException {
     *
     * Date d = new Date(); String fileName = d.toString().replace(":",
     * "_").replace(" ", "_")+".jpg";
     *
     *
     *
     * File screeshot = ((TakesScreenshot)
     * element).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(screeshot, new
     * File(".//screenshot//"+"Element_"+fileName)); }
     *
     */
}
