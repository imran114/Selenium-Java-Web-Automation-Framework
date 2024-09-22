package utils.random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.selenium_utils.SeleniumUtils;

import java.util.List;
import java.util.Random;

public class RandomUtils extends SeleniumUtils {
    public RandomUtils(WebDriver driver) {
        super(driver);
    }

    public static WebElement returnRandomWebElementFromList(List<WebElement> webElementList) {
        Random random;
        if (webElementList.size() < 0) {
            throw new IllegalArgumentException("WebElement List if empty or null");
        } else {
            random = new Random();
            int getIndex = random.nextInt(webElementList.size());
            return webElementList.get(getIndex);
        }
    }
}
