package test_base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;
import utils.email.EmailBody;
import utils.email.EmailSender;
import utils.reporter.ExtentReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import static test_base.WebDriverManager.getDriver;

// This class sets up and tears down the test environment before and after each suite.
public class BaseClass {

    public static ExtentReport extentReport;
    public static EmailBody emailBody;
    private EmailSender emailSender;
    private XmlTest xmlTest;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);


    // This method sets up the test environment before each suite.
    @BeforeSuite
    public void setUp() throws IOException {
        logger.info("Setting up test suite...");
        extentReport = new ExtentReport();
        emailSender = new EmailSender();
        extentReport.createReport();
        setExtentReport(extentReport);
        getDriver().get("https://www.google.com/");
        getDriver().manage().window().maximize();
        emailBody = new EmailBody();
        emailBody.clearEmailBody();
        emailBody.enterTextToEmailBody("");
        emailBody.enterTextToEmailBody("Test Project");
        emailBody.enterTextToEmailBody("Platform: Web");
        logger.info("Test suite setup complete.");
    }

    public void setExtentReport(ExtentReport extentReport) {
        this.extentReport = extentReport;
    }
    // This method tears down the test environment after each suite.
    @AfterSuite
    public void tearDown() throws MalformedURLException, FileNotFoundException {
        logger.info("Tearing down test suite...");

        // Finalize and generate the extent report by flushing any pending logs and resources.
        extentReport.flushReport();

        String recipient = "imranullah114@gmail.com";
        String subject = "[Project Name] Automation Report";
        emailSender.sendEmail(recipient, subject);
        // Close the application and terminate the driver session
        getDriver().quit();
        logger.info("Test suite teardown complete.");
    }


}