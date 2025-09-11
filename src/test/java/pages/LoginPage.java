package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this); // 💥 This is required
    }

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "submit")
    WebElement send;

    @FindBy(id = "login")
    WebElement form;

    @FindBy(className = "post-title")
    WebElement hTittle;

    @FindBy(id = "error")
    WebElement errorMessage;

    SoftAssert softAssert = new SoftAssert();

    public void checkIfTheLoginPageIsDisplay() {
        boolean actual = checkElementIsDisplayed(form);
        softAssert.assertEquals(actual, true);
        softAssert.assertAll();
    }

    public void fillAllData(String username1, String password1) {
        sendTextByElement(username, username1);
        sendTextByElement(password, password1);
    }

    public void clickOnLoginButton() {
        clickElement(send);
    }

    public void checkHomePageIsDisplayed() {
        boolean actual = checkElementIsDisplayed(hTittle);
        softAssert.assertEquals(actual, true);
        softAssert.assertAll();
    }
    public void checkIfErrorMessageIsAppears() {

        boolean isDisplayed = checkElementIsDisplayed(errorMessage);
        softAssert.assertEquals(isDisplayed, true);
        String actualErrorMessage = getTextByElement(errorMessage);

        String expectedErrorPassword = "Your password is invalid!";
        String expectedErrorUsername = "Your username is invalid!";

        boolean matchesExpected =
                actualErrorMessage.equals(expectedErrorPassword) ||
                        actualErrorMessage.equals(expectedErrorUsername);

        softAssert.assertTrue(matchesExpected,
                "Actual error message '" + actualErrorMessage + "' does not match any expected error messages.");

        System.out.println("Actual error message: " + actualErrorMessage);

        softAssert.assertAll();
    }


}
