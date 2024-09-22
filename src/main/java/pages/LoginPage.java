package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.common_components.button_actions.ButtonActions;
import utils.common_components.edit_text_fields.EditText;
import utils.selenium_utils.SeleniumUtils;

public class LoginPage extends SeleniumUtils {


    private final EditText editText;
    private final ButtonActions buttonActions;
    private final By emailTextFieldLocator = By.xpath("//input[@id='email-text-field']");
    private final By passwordFieldLocator = By.xpath("//input[@id='password-text-field']");
    private final By signInButtonLocator = By.xpath("//button[@id='signin Button']");


    public LoginPage(WebDriver driver) {
        super(driver); // Pass the driver to the parent class
        this.editText = new EditText(driver);
        this.buttonActions = new ButtonActions(driver);
    }

    public String enterEmail(String email) {
        return editText.enterTextBoxValue(emailTextFieldLocator, email, "Email");
    }

    public String enterPassword(String password) {
        return editText.enterTextBoxValue(passwordFieldLocator, password, "Email");
    }

    public String clickSignInButton() {
        return buttonActions.clickOnButton(signInButtonLocator, "Sign in");
    }
}
