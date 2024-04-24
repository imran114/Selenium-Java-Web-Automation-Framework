package extent_reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;


public class ExtentReport  {
    private WebDriver driver;
    ExtentSparkReporter spark;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public ExtentReport(WebDriver driver) {
        this.driver = driver;

    }

    //Created Extent Report Html File
    public void extentReporter() {
        spark = new ExtentSparkReporter("src/test/java/reports/report.html");

        extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
    }

    public void logTestNameAndDescription(String name, String description){
        extentTest = extentReports.createTest(name, description);
    }

    public void logTestInfo(String testInfo){
        extentTest.log(Status.INFO,testInfo);
    }

    public void logPassedTestSteps(String logPassMessage){
        extentTest.pass(logPassMessage);
    }
    public void logFailTestSteps(String logFailMessage){
        extentTest.fail(logFailMessage);
    }

    public void flushExtentReport(){
        extentReports.flush();
    }

}

