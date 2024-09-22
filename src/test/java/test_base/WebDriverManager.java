package test_base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
// This class manages the WebDriver.
public class WebDriverManager {

    private static WebDriver driver;

    // This method returns the for the specified device name.
    public static WebDriver getDriver() {
        // Check if the driver is already initialized
        if (driver == null) {
            driver = new ChromeDriver();
        }
        log.info("Driver obtained.");
        return driver;
    }
}

