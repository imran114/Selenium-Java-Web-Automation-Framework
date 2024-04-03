package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TripShepherdTest extends BaseClass {

    @Test(priority = 1)
    public void clickOnFindCityField() {
        actualResult = tripShepherdPage.tapOnFindCityField();
        extentReport.logTestNameAndDescription("Click City Field", "Click on the City Field");
        extentReport.logTestInfo("Clicked on the City Field Successfully");
        expectedResult = "Pass, Find City Field Clicked";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 2)
    public void clickOnAustinCity() {
        actualResult = tripShepherdPage.tapOnAustinCityField();
        extentReport.logTestNameAndDescription("Click Austin City", "Clicking on the Austin City ");
        extentReport.logTestInfo("Clicked on the Austin City Field Successfully");
        expectedResult = "Pass, Austin City Clicked";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 3)
    public void clickOnPrivateTourFilter() {
        actualResult = tripShepherdPage.tapOnPrivateTourFilter();
        extentReport.logTestNameAndDescription("Click Private Tour Filter", "Clicking on the Private Tour Filter");
        extentReport.logTestInfo("Clicked on the Private Tour Filter Successfully");
        expectedResult = "Pass, Private Tour Filter selected";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 4)
    public void clickOnCardTour() {
        actualResult = tripShepherdPage.tapOnCardTour();
        extentReport.logTestNameAndDescription("Click On Card Tour", "Clicking On Card Tour");
        extentReport.logTestInfo("Clicked On Card Tour Successfully");
        expectedResult = "Pass, Card Tour Clicked";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 5)
    public void clickOnDateDropdown() {
        actualResult = tripShepherdPage.tapOnDateDropdown();
        extentReport.logTestNameAndDescription("Click Date drop down", "Clicking on Date drop down");
        extentReport.logTestInfo("Clicked on the Date drop down Successfully");
        expectedResult = "Pass, Date Dropdown Clicked";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 6)
    public void selectDateFromCalendar() {
        actualResult = tripShepherdPage.selectDate();
        extentReport.logTestNameAndDescription("Select Date from Calendar", "Selecting Date from Calendar");
        extentReport.logTestInfo("Selected Date from Calendar Successfully");
        expectedResult = "Pass, Date Selected";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 7)
    public void selectTime() {
        actualResult = tripShepherdPage.selectTime();
        extentReport.logTestNameAndDescription("Select Time", "Selecting Time");
        extentReport.logTestInfo("Selected time successfully");
        expectedResult = "Pass, Time Selected";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 8)
    public void clickOnPassengerDropdown() {
        actualResult = tripShepherdPage.tapOnPassengerDropdown();
        extentReport.logTestNameAndDescription("Click Passenger Drop down", "Clicking on Passenger Drop down");
        extentReport.logTestInfo("Clicked on the Passenger Drop down Successfully");
        expectedResult = "Pass, Passenger dropdown clicked";
        Assert.assertEquals(actualResult, expectedResult);

    }


    @Test(priority = 9)
    public void verifyPassengerPrice() throws IOException {
        actualResult = tripShepherdPage.verifyPassengerPrice();
        extentReport.logTestNameAndDescription("Verify Passenger Price", "Verify Passenger Price is matched with the price displayed on the book now button");
        extentReport.logTestInfo("Verified Passenger Price and price displayed on book now button Successfully");
        expectedResult = "Pass, prices matched";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(priority = 11)
    public void clickOnBookNowButton() {
        actualResult = tripShepherdPage.tapOnBookNowButton();
        extentReport.logTestNameAndDescription("Click book now button", "Click on book now button to book Tour");
        extentReport.logTestInfo("Clicked on Book Now button");
        expectedResult = "Pass, Book now button Clicked";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 12)
    public void enterName() {
        actualResult = tripShepherdPage.enterName("imran ullah");
        extentReport.logTestNameAndDescription("Enter name", "Enter name of card owner");
        extentReport.logTestInfo("Card owner name entered");
        expectedResult = "Pass, Name entered";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 13)
    public void enterEmail() {
        actualResult = tripShepherdPage.enterEmail("imranullah114@gmail.com");
        extentReport.logTestNameAndDescription("Enter email", "Enter email of card owner");
        extentReport.logTestInfo("Email of card owner entered");
        expectedResult = "Pass, Email entered";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 14)
    public void enterPhoneNumber() {
        actualResult = tripShepherdPage.enterPhoneNumber("+1 613 555 0176");
        extentReport.logTestNameAndDescription("Enter phone number", "Enter phone number of card owner");
        extentReport.logTestInfo("Phone number of card owner entered");
        expectedResult = "Pass, Phone number entered";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 15)
    public void enterCardNumber() {
        actualResult = tripShepherdPage.enterCardNumber("5011054488597827");
        extentReport.logTestNameAndDescription("Enter Card Number", "Enter Card Number displayed on card");
        extentReport.logTestInfo("Card Number Entered");
        expectedResult = "Pass, Card number entered";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 16)
    public void enterCardExpiry() {
        actualResult = tripShepherdPage.enterCardExpiryNumber("12/26");
        extentReport.logTestNameAndDescription("Enter Card Expiry", "Enter Expiry date of card");
        extentReport.logTestInfo("Card Expiry entered");
        expectedResult = "Pass, Card expiry entered";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(priority = 17)
    public void enterCVV() {
        actualResult = tripShepherdPage.enterCardCVVNumber("123");
        extentReport.logTestNameAndDescription("Enter Card CVV", "Enter CVV displayed on card");
        extentReport.logTestInfo("Card CVV entered");
        expectedResult = "Pass, Card cvv entered";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(priority = 18)
    public void clickOnPayButton() {
        actualResult = tripShepherdPage.tapOnPayNowButton();
        extentReport.logTestNameAndDescription("Click pay button", "Click on pay button to complete transaction");
        extentReport.logTestInfo("Pay now button clicked");
        expectedResult = "Pass, Pay now button clicked";
        Assert.assertEquals(actualResult, expectedResult);
    }
}
