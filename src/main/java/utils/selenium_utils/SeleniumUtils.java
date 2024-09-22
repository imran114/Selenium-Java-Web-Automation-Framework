package utils.selenium_utils;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Slf4j
public class SeleniumUtils {
    protected String result;
    protected WebDriverWait wait;
    protected WebDriver driver;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));

    }

    public String clearText(By locator) {
        try {
            WebElement element = returnWebElement(locator);
            waitForClickable(locator);
            element.clear();
        } catch (Exception e) {
            log.error("Element not found. Cannot perform click.", e);
        }
        return "Pass, Text field cleared";
    }


    public void click(By locator) {
        try {
            WebElement element = returnWebElement(locator);
            waitForClickable(locator);
            element.click();
        } catch (NoSuchElementException | IOException e) {
            log.error("Element not found. Cannot perform click.", e);
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            WebElement element = returnWebElement(locator);
            element.click();
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            log.error("Element not found. Cannot perform sendKeys.", e);
            throw e;
        }
    }

    public boolean isDisplayedWithOutWait(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayedWithWait(By locator, int seconds) {
        try {
            waitForVisibility(locator, seconds);
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | TimeoutException | IOException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(By locator) {

        try {
            waitForVisibility(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | TimeoutException | IOException e) {
            return false;
        }
    }

    public String returnAttribute(By locator, String attribute) {
        try {
            WebElement element = returnWebElement(locator);
            return element.getAttribute(attribute);
        } catch (NoSuchElementException e) {
            log.error("Element not found. Cannot return attribute.", e);
            throw e;
        }
    }

    public String returnText(By locator) {
        try {
            WebElement element = returnWebElement(locator);
            return element.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Element not found. Cannot return text.", e);
            throw e;
        }
    }

    public String returnContentDesc(By locator) throws IOException {
        WebElement element = returnWebElement(locator);
        return element.getAttribute("content-desc");
    }


    public List<WebElement> returnWebElementsList(By locator) {
        try {
            waitForVisibility(locator);
            return driver.findElements(locator);
        } catch (NoSuchElementException | IOException e) {
            log.error("Elements not found.", e);
            return Collections.emptyList(); // Return an empty list instead of throwing an exception
        }
    }


    public WebElement returnWebElement(By locator) {
        try {
            waitForVisibility(locator);
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            getFailedElementScreenShot();  // Capture the screenshot if the element is not found
            log.error("Element not found.", e);
            throw e;  // Re-throwing NoSuchElementException
        } catch (IOException io) {
            log.error("Failed to capture screenshot due to IO issue.", io);
            throw new RuntimeException("Failed to capture screenshot.", io);  // You can choose how to handle IO exceptions
        }
    }


    public WebElement returnWebElement(By locator, int seconds) throws IOException {
        try {
            waitForVisibility(locator, seconds);
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            getFailedElementScreenShot();
            log.error("Element not found.", e);
            throw e;
        }
    }


    public List<By> returnByElements(By locator) throws IOException {
        try {
            waitForVisibility(locator);
            return Collections.singletonList(locator);
        } catch (NoSuchElementException e) {
            getFailedElementScreenShot();
            log.error("Element not found.", e);
            throw e;
        }
    }


    public WebElement waitForElementToBeVisibleWithCustomWait(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibility(By locator, int seconds) throws IOException {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException | NoSuchElementException e) {
            getFailedElementScreenShot();
            log.error("Element not visible after waiting.", e);
            throw e;
        }
    }

    public void waitForClickable(By locator) throws IOException {
        try {
            waitForVisibility(locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException | NoSuchElementException e) {
            getFailedElementScreenShot();
            log.error("Element not clickable after waiting.", e);
            throw e;
        }
    }


    public void waitForVisibility(By locator) throws IOException {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            getFailedElementScreenShot();
            log.error("Element not visible after waiting.", e);
            throw e;
        }
    }


    public void waitForVisibility(WebElement locator) throws IOException {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated((By) locator));
        } catch (TimeoutException e) {
            getFailedElementScreenShot();
            log.error("Element not visible after waiting.", e);
            throw e;
        }
    }

    public void waitForClickable(WebElement locator) throws IOException {
        try {
            waitForVisibility(locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            getFailedElementScreenShot();
            log.error("Element not clickable after waiting.", e);
            throw e;
        }
    }


//    public void getFailedElementScreenShot() {
//        String fileName = "screenShot.png";
//        String pathToSaveFile = "src/main/resources/screenShots/" + fileName;
//        try {
//            var getScreenShot = (TakesScreenshot) driver;
//            File screenShot = getScreenShot.getScreenshotAs(OutputType.FILE);
//            Files.move(screenShot, new File(pathToSaveFile));
//        } catch (Exception exception) {
//            log.error("Failed to capture screenshot.", exception);
//            throw exception;
//        }
//    }


    public void getFailedElementScreenShot() {
        String fileName = "screenShot.png";
        String pathToSaveFile = "src/main/resources/screenShots/" + fileName;
        try {
            TakesScreenshot getScreenShot = (TakesScreenshot) driver;
            File screenShot = getScreenShot.getScreenshotAs(OutputType.FILE);
            Files.move(screenShot, new File(pathToSaveFile));  // Convert File to Path
        } catch (IOException ioException) {
            log.error("Failed to save screenshot due to IO issue.", ioException);
            throw new RuntimeException("Screenshot save failed.", ioException);
        } catch (Exception exception) {
            log.error("Unexpected error occurred while capturing screenshot.", exception);
            throw new RuntimeException("Screenshot capture failed.", exception);
        }
    }


}
