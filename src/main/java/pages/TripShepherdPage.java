package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BaseClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripShepherdPage extends BaseClass {
    private final By findCityTextFieldXpath;
    private final By austinCityButtonXpath;
    private final By privateTourFilterXpath;
    private final By cardTourXpath;
    private final By dateDropdownXpath;
    private final By dateXpath;
    private final By timeXpath;
    private final By passengerDropdownXpath;
    private final By passengersPriceXpath;
    private final By bookNowButtonXpath;
    private final By phoneNumberTextFieldXpath;
    private final By nameTextFieldXpath;
    private final By emailTextFieldXpath;
    private final By cardNumberTextFieldXpath;
    private final By cardExpiryTextFieldXpath;
    private final By cardCVVTextFieldXpath;
    private final By payButtonXpath;


    public TripShepherdPage(WebDriver driver) {
        BaseClass.driver = driver;
        findCityTextFieldXpath = By.xpath("//button[contains(span/text(), 'Find your city')]");
        austinCityButtonXpath = By.xpath("//img[@alt='Austin']");
        privateTourFilterXpath = By.xpath("//div[contains(@class, 'FeaturedNavSec__NavMenu') and text()='Private Tours']");
        cardTourXpath = By.xpath("//div[@id=\"tour-section\"]/div");
        dateDropdownXpath = By.xpath("//div[contains(@class, 'border') and contains(@class, 'rounded') and contains(@class, 'border-gray-300')]");
        dateXpath = By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div[2]/div[2]/div/div/div/div/div/div[3]/div/div[2]/div/div/div/div[2]/button[19]");
        timeXpath = By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div[2]/div[2]/div/div/div/div/div/div[2]/div[2]/div");
        passengerDropdownXpath = By.xpath("//span[@class='text-black'][contains(text(), '1 Passenger')]");
        passengersPriceXpath = By.xpath("//p[contains(@class, 'text-black') and contains(text(),'5')]//span[contains(@class, 'font-semibold')]");
        bookNowButtonXpath = By.xpath("//button[contains(@class, 'bg-[#FD5D5A]') and contains(text(), 'Book now')]");
        phoneNumberTextFieldXpath = By.xpath("//input[@class=\"PhoneInputInput\"]");
        nameTextFieldXpath = By.xpath("//input[@placeholder=\"Name\"]");
        cardNumberTextFieldXpath = By.xpath("//input[@id='Field-numberInput' and @type='text' and @inputmode='numeric' and @name='number' and @placeholder='1234 1234 1234 1234']");
        cardExpiryTextFieldXpath = By.xpath("//input[@id=\"Field-expiryInput\"]");
        cardCVVTextFieldXpath = By.xpath("//input[@id=\"Field-cvcInput\"]");
        emailTextFieldXpath = By.xpath("//input[@placeholder=\"Email\"]");
        payButtonXpath = By.xpath("//button[@id=\"ga4-event-listener-checkout\"]");
    }

    public String tapOnFindCityField() {
        try {
            waitForClickable(findCityTextFieldXpath);
            driver.findElement(findCityTextFieldXpath).click();
            result = "Pass, Find City Field Clicked";
        } catch (Exception e) {
            result = "Fail, unable to click City Field";
        }
        return result;
    }

    public String tapOnAustinCityField() {
        try {
            waitForClickable(austinCityButtonXpath);
            waitForVisibility(austinCityButtonXpath);
            driver.findElement(austinCityButtonXpath).click();

            result = "Pass, Austin City Clicked";
        } catch (Exception e) {
            result = "Fail, unable to click Austin City";
        }
        return result;
    }

    public String tapOnPrivateTourFilter() {
        try {
            Thread.sleep(3000);
            waitForClickable(privateTourFilterXpath);
            driver.findElement(privateTourFilterXpath).click();
            result = "Pass, Private Tour Filter selected";
        } catch (Exception e) {
            result = "Fail, unable to select Private Tour Filter ";
        }
        return result;
    }

    public String tapOnCardTour() {
        try {
//            Thread.sleep(3000);
            waitForClickable(cardTourXpath);
            driver.findElement(cardTourXpath).click();
            result = "Pass, Card Tour Clicked";
        } catch (Exception e) {
            result = "Fail, unable to click Card Tour";
        }
        return result;
    }

    public String verifyPassengerPrice() throws IOException {
        String priceDisplayedInDropdown = returnTextOfLocator(passengersPriceXpath);
        String extractedDigitsFromDropdownPrice = extractDigitsFromString(priceDisplayedInDropdown);
        savePassengerPriceToPropertiesFile(extractedDigitsFromDropdownPrice);
        driver.findElement(passengersPriceXpath).click();
        String priceDisplayedOnBookNowButton = returnTextOfLocator(bookNowButtonXpath);
        String extractedDigitsFromBookNowPrice = extractDigitsFromString(priceDisplayedOnBookNowButton);
        System.out.println("extractedDigitsFromDropdownPrice " + extractedDigitsFromDropdownPrice);
        System.out.println("extractedDigitsFromBookNowPrice " + extractedDigitsFromBookNowPrice);
        return priceDisplayedInDropdown.contains(extractedDigitsFromBookNowPrice) ? "Pass, prices matched" : "Fail, price doesn't matched";

    }

    public String tapOnBookNowButton() {
        try {
            waitForClickable(bookNowButtonXpath);
            driver.findElement(bookNowButtonXpath).click();
            result = "Pass, Book now button Clicked";
        } catch (Exception e) {
            result = "Fail, unable to click Book now button";
        }
        return result;
    }

    public String tapOnDateDropdown() {
        try {
            Thread.sleep(3000);
            waitForClickable(dateDropdownXpath);
            driver.findElement(dateDropdownXpath).click();
            result = "Pass, Date Dropdown Clicked";
        } catch (Exception e) {
            result = "Fail, unable to click Date Dropdown";
        }
        return result;
    }

    public String selectDate() {
        try {
            Thread.sleep(4000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)");
            waitForVisibility(dateXpath);
            WebElement element = driver.findElement(dateXpath);
            waitForClickable(dateXpath);
            element.click();
            result = "Pass, Date Selected";
        } catch (Exception e) {
            result = "Fail, unable to select Date";
        }
        return result;
    }

    public String selectTime() {
        try {
            waitForClickable(timeXpath);
            driver.findElement(timeXpath).click();
            result = "Pass, Time Selected";
        } catch (Exception e) {
            result = "Fail, unable to select Time";
        }
        return result;
    }

    public String tapOnPassengerDropdown() {
        try {
            waitForClickable(passengerDropdownXpath);
            driver.findElement(passengerDropdownXpath).click();
            result = "Pass, Passenger dropdown clicked";
        } catch (Exception e) {
            result = "Fail, unable to click Passenger dropdown";
        }
        return result;
    }

    public String tapOnPayNowButton() {
        try {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,400)");
            waitForClickable(payButtonXpath);
            driver.findElement(payButtonXpath).click();
            driver.switchTo().defaultContent();
            js.executeScript("window.scrollBy(0,-400)");
            result = "Pass, Pay now button clicked";
            Thread.sleep(5000);
        } catch (Exception e) {
            result = "Fail, unable to click Pay now button";
        }
        return result;
    }

    private void savePassengerPriceToPropertiesFile(String value) throws IOException {
        String filePath = "src/main/resources/passenger_price_details.properties";
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
            properties.setProperty("price", value);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            properties.store(fileOutputStream, "passenger price details");
        }
    }

    public String returnTextOfLocator(By locator) {
        return driver.findElement(locator).getText();
    }

    public String extractDigitsFromString(String input) {
        Pattern pattern = Pattern.compile("\\$\\d+");
        Matcher matcher = pattern.matcher(input);

        String extractedDigits = "";
        if (matcher.find()) {
            extractedDigits = matcher.group();
        }

        return extractedDigits;
    }

    public String enterPhoneNumber(String number) {
        driver.findElement(phoneNumberTextFieldXpath).clear();
        driver.findElement(phoneNumberTextFieldXpath).sendKeys(number);
        return "Pass, Phone number entered";
    }

    public String enterName(String name) {
        driver.findElement(nameTextFieldXpath).sendKeys(name);
        return "Pass, Name entered";
    }

    public String enterEmail(String email) {
        driver.findElement(emailTextFieldXpath).sendKeys(email);
        return "Pass, Email entered";
    }

    public String enterCardNumber(String number) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Secure payment input frame']"));
        driver.switchTo().frame(iframe);
        driver.findElement(cardNumberTextFieldXpath).click();
        driver.findElement(cardNumberTextFieldXpath).sendKeys(number);
        driver.switchTo().defaultContent();
        return "Pass, Card number entered";
    }

    public String enterCardCVVNumber(String number) {
        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Secure payment input frame']"));
        driver.switchTo().frame(iframe);
        driver.findElement(cardCVVTextFieldXpath).sendKeys(number);
        driver.switchTo().defaultContent();
        return "Pass, Card cvv entered";
    }

    public String enterCardExpiryNumber(String number) {
        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Secure payment input frame']"));
        driver.switchTo().frame(iframe);
       WebElement element = driver.findElement(cardExpiryTextFieldXpath);
       element.sendKeys(number);
        driver.switchTo().defaultContent();
        return "Pass, Card expiry entered";
    }

}
