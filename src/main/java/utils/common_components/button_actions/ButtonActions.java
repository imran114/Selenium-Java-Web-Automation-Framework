package utils.common_components.button_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.selenium_utils.SeleniumUtils;

public class ButtonActions extends SeleniumUtils {

    public ButtonActions(WebDriver driver) {
        super(driver);
    }


    /*******************
     * General Button Method
     * */
    public String clickOnButton(By locator, String buttonName) {
        try {
            click(locator);
            result = "Pass, user is able to click on the " + buttonName;
        } catch (TimeoutException | NoSuchElementException e) {
            result = "Fail, user is unable to click on the " + buttonName;
        }
        return result;
    }
}
