package utils.common_components.edit_text_fields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.selenium_utils.SeleniumUtils;

public class EditText extends SeleniumUtils {

    public EditText(WebDriver driver) {
        super(driver);
    }

    public String enterTextBoxValue(By locator, String valueToEnter, String textBoxName) {
        try {
            waitForVisibility(locator);
            click(locator);
            clearText(locator);
            sendKeys(locator, valueToEnter);
            return "Pass, user is able to enter the text " + valueToEnter + " in the " + textBoxName;
        } catch (Exception e) {
            return "Fail, user is unable to enter the text" + valueToEnter + " in the " + textBoxName;
        }
    }

}
