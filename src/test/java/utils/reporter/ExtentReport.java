package utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    private static final Logger logger = LogManager.getLogger(ExtentReport.class);
    WebDriver driver;
    private ExtentReports extentReports;
    private ExtentSparkReporter extentSparkReporter;
    private ExtentTest extentTest;
    Map<String, ExtentTest> parentTestsMap = new HashMap<>();
    Map<String, ExtentTest> childTestsMap = new HashMap<>();
    Map<String, ExtentTest> grandChildTestsMap = new HashMap<>();
    private String screenshotPath = "src/main/resources/screenShots/screenShot.png";


    public ExtentReport() {
        extentReports = new ExtentReports();
    }


    public void createReport() {
        extentSparkReporter = new ExtentSparkReporter("src/test/resources/reports/report.html");
        extentSparkReporter.config().setDocumentTitle("[Project Name] Automation Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(extentSparkReporter);
    }

    public void createTest(String testName, String testDescription) {
        if (!parentTestsMap.containsKey(testName)) {
            ExtentTest parentTest = extentReports.createTest(testName, testDescription)
            .assignCategory(testName).assignDevice("Web Portal");
            parentTestsMap.put(testName, parentTest);
            extentTest = parentTest;
        }
    }

    public void testPass(String passInfo) {
        extentTest.pass(passInfo);
    }

    public void testFail(String failInfo) {
        try {
            extentTest.assignCategory(failInfo);
            extentTest.fail(failInfo);
            getFailedElementScreenShot();
        } catch (Exception e) {
            logger.error("Error while adding screenshot for failed test", e);
        }
    }

    public void testSkip(String reasonToSkip) {
        extentTest.skip(reasonToSkip);
    }

    public void testInfo(String testInfo) {
        if (extentTest == null) {
            logger.error("extentTest is null");
            throw new IllegalStateException("extentTest is null");
        }
        extentTest.info(testInfo);
    }
    public void assertHandler(String statement) {
        if (statement.contains("Skip")) {
            testSkip(statement);
        } else {
            if (statement.contains("Pass")) {
                Assert.assertTrue(statement.contains("Pass"));
            } else if (statement.contains("Fail")) {
                //Assert.assertTrue(true,false);
                Assert.assertEquals("Pass","Fail");
                // Assert.assertTrue(statement.contains("Pass"));
            }
        }
    }

    public void logStepResult(String stepResult) {
        if (stepResult.contains("Pass")) {
            testPass(stepResult);
        } else if (stepResult.contains("Fail")) {
            testFail(stepResult);
        } else {
            testInfo(stepResult);
        }
    }

    public void flushReport() {
        extentReports.flush();
        String screenshotPath = "src/main/resources/screenShots/screenShot.png";
        // Delete the screenshot file
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.delete()) {
            logger.info("Screenshot file deleted successfully.");
        } else {
            logger.error("Failed to delete the screenshot file.");
        }
    }

    public void logChildTestNameAndDescription(String parentTestName, String childTestName) {
        if (parentTestsMap.containsKey(parentTestName)) {
            extentTest = parentTestsMap.get(parentTestName).createNode(childTestName).assignCategory(parentTestName);
            childTestsMap.put(childTestName, extentTest);
        }
    }

    public void logGrandChildTestNameAndDescription(String parentTestName, String childTestName, String grandChildName) {
        if (parentTestsMap.containsKey(parentTestName) && childTestsMap.containsKey(childTestName)) {
            ExtentTest parentTest = parentTestsMap.get(parentTestName);
            ExtentTest childTest = childTestsMap.get(childTestName);
            if (childTest == null) {
                childTest = parentTest.createNode(childTestName).assignCategory(parentTestName);
                childTestsMap.put(childTestName, childTest);
            }
            if (!grandChildTestsMap.containsKey(grandChildName)) {
                extentTest = childTest.createNode(grandChildName).assignCategory(parentTestName);
                grandChildTestsMap.put(grandChildName, extentTest);
            }
            if (isFilePresent(screenshotPath)) deleteScreenShot();
        }
    }

    public void getFailedElementScreenShot() throws IOException {
        String fileName = "screenShot.png";
        String pathToSaveFile = "src/main/resources/screenShots/" + fileName;
        try {
            var getScreenShot = (TakesScreenshot) driver;
            File screenShot = getScreenShot.getScreenshotAs(OutputType.FILE);
            Files.move(screenShot, new File(pathToSaveFile));
        } catch (Exception exception) {
            throw exception;
        }
    }

    private void deleteScreenShot() {
        try {
            screenshotPath = "src/main/resources/screenShots/screenShot.png";

            // Delete the screenshot file
            File screenshotFile = new File(screenshotPath);
            if (screenshotFile.exists()) {
                boolean deletionSuccessful = screenshotFile.delete();
                if (deletionSuccessful) {
                    System.out.println("Screenshot file deleted successfully.");
                } else {
                    System.out.println("Failed to delete the screenshot file.");
                }
            } else {
                System.out.println("Screenshot file not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while deleting the screenshot file.");
        }
    }

    private boolean isFilePresent(String screenshotPath) {
        return new File(screenshotPath).exists();
    }
}
 