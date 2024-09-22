package utils.common_components.scroll_methods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.selenium_utils.SeleniumUtils;

import java.io.IOException;
import java.time.Duration;

public class ScrollingMethods extends SeleniumUtils {

    private static final Logger logger = LogManager.getLogger(ScrollingMethods.class);

    private final WebDriver driver;

    public ScrollingMethods(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void swipeUp(WebElement element) {
        logger.info("Swiping up...");
        swipe(element, Direction.UP);
    }


    public void swipeDown(WebElement element) {
        logger.info("Swiping down...");
        swipe(element, Direction.DOWN);
    }


    public String scrollDown(By targetElement, By scrollAreaElement) {
        try {
            performScroll(targetElement, scrollAreaElement, Direction.DOWN);

            if (isElementDisplayed(targetElement)) {
                return "Pass, scrolled up";
            } else {
                return "Fail, unable to scroll up";
            }
        } catch (Exception e) {
            return "Fail, exception occurred while scrolling up: " + e.getMessage();
        }
    }

    public void swipeLeft(WebElement element) {
        logger.info("Swiping left...");

        swipe(element, Direction.LEFT);
    }

    public void swipeRight(WebElement element) {
        logger.info("Swiping right...");

        swipe(element, Direction.RIGHT);
    }

    protected void swipe(WebElement element, Direction direction) {
        Rectangle rect = element.getRect();
        int startX;
        int startY;
        int endX;
        int endY;

        switch (direction) {
            case UP:
                startX = rect.getX() + rect.getWidth() / 2;
                startY = rect.getY() - 150 + rect.getHeight() - 70;
                endX = startX;
                endY = rect.getY() + 70;
                break;
            case DOWN:
                startX = rect.getX() + rect.getWidth() / 2;
                startY = rect.getY() + 10;
                endX = startX;
                endY = rect.getY() + rect.getHeight() - 10;
                break;
            case LEFT:
                startX = rect.getX() + rect.getWidth() - 10;
                startY = rect.getY() + rect.getHeight() / 2;
                endX = rect.getX() + 10;
                endY = startY;
                break;
            case RIGHT:
                startX = rect.getX() + 10;
                startY = rect.getY() + rect.getHeight() / 2;
                endX = rect.getX() + rect.getWidth() - 10;
                endY = startY;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

//        driver.perform(Collections.singletonList(pointerInputSequence(startX,startY, endX, endY)));
    }

    private void swipeFaturatiList(WebElement element, Direction direction) {
        Rectangle rect = element.getRect();
        int startX;
        int startY;
        int endX;
        int endY;

        switch (direction) {
            case UP:
                startX = rect.getX() + rect.getWidth() / 2;
                startY = rect.getY() - 330 + rect.getHeight() - 230;
                endX = startX;
                endY = rect.getY() - 230;
                break;
            case DOWN:
                startX = rect.getX() + rect.getWidth() / 2;
                startY = rect.getY() + 10;
                endX = startX;
                endY = rect.getY() + rect.getHeight() - 10;
                break;
            case LEFT:
                startX = rect.getX() + rect.getWidth() - 10;
                startY = rect.getY() + rect.getHeight() / 2;
                endX = rect.getX() + 10;
                endY = startY;
                break;
            case RIGHT:
                startX = rect.getX() + 10;
                startY = rect.getY() + rect.getHeight() / 2;
                endX = rect.getX() + rect.getWidth() - 10;
                endY = startY;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
//         driver.perform(Collections.singletonList(pointerInputSequence(startX,startY, endX, endY)));
    }


    private Sequence pointerInputSequence(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(300)));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return sequence;
    }

    protected enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    public String scrollDown() {
        try {
            swipeDown(driver.findElement(By.xpath("(//android.view.ViewGroup)[4]")));
            return "Pass, scrolled down";
        } catch (Exception e) {
            return "fail, unable to scroll down";
        }
    }

    private void performScroll(By targetElement, By scrollAreaLocator, Direction direction) throws IOException {
        int maxScrollAttempts = 10;
        try {
            waitForVisibility(scrollAreaLocator);
            int attempts = 0;
            boolean isVisible = isDisplayedWithWait(targetElement, 2);
            while (!isVisible && attempts < maxScrollAttempts) {
                swipe(returnWebElement(scrollAreaLocator), direction);
                isVisible = isDisplayedWithWait(targetElement, 3);
                attempts++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String scrollUp(By targetElement, By scrollAreaElement) {

        try {
            performScroll(targetElement, scrollAreaElement, Direction.UP);
            if (isElementDisplayed(targetElement)) {
                return "Pass, scrolled up";
            } else {
                return "Fail, unable to scroll up";
            }
        } catch (Exception e) {
            return "Fail, exception occurred while scrolling up: " + e.getMessage();
        }
    }

}
