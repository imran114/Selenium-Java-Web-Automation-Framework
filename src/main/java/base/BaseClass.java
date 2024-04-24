package base;


import extent_reporter.ExtentReport;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.TripShepherdPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClass  {
    protected static WebDriver driver;
    protected String result;
    private static WebDriverWait wait;
    public static ExtentReport extentReport;
    protected String actualResult;
    public String expectedResult;
    public static TripShepherdPage tripShepherdPage;

    @BeforeSuite
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver","drivers/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, TimeUnit.SECONDS.ordinal());
        tripShepherdPage = new TripShepherdPage(driver);
        driver.get("https://www.tripshepherd.com"); //
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        extentReport = new ExtentReport(driver);
        extentReport.extentReporter();

    }
    public void waitForClickable(By locator) throws IOException {
        try {
            waitForVisibility(locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw e;
        }
    }
    public void waitForVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw e;
        }
    }
    @AfterSuite
    public static void tearDown() {
        if (driver != null) {
            driver.close();
        }
        extentReport.flushExtentReport();
    }
}
