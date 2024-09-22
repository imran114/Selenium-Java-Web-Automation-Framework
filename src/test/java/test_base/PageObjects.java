package test_base;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import utils.file_reader.PropertiesFileReader;
import static test_base.WebDriverManager.getDriver;

@Slf4j
// This class initializes the page objects before each test class.
public class PageObjects extends BaseClass {
    protected String actualResult = "";
    protected LoginPage loginPage;
    protected String useCaseName;


    protected PropertiesFileReader propertiesFileReader;

    @BeforeClass
    public void getLatestDriver() {
        log.info("Initializing WebDriver...");
        getDriver(); // Initialize WebDriver
        log.info("Initializing page objects...");
        loginPage = new LoginPage(getDriver()); // Pass the driver to the page class

        log.info("Page objects initialized.");
    }

    protected void parentTest(String useCaseName, String description) {
        extentReport.createTest(useCaseName, description);
    }
}
