package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.ExcelReader;

import java.util.Map;

public class LoginTest {

    LoginPage loginPage = new LoginPage();

    @Given("the user opens the login page")
    public void goToCrmWebsite() {
        loginPage.checkIfTheLoginPageIsDisplay();
    }

    Map<String, String> testData = ExcelReader.loadKeyValueData("src/test/resources/testData.xlsx", "Login");

    @When("the user logs in using keys {string} and {string}")
    public void fillAllData(String usernameKey, String passwordKey) {
        String username = testData.get(usernameKey);
        String password = testData.get(passwordKey);
        System.out.println("Warning: Missing key or value cell at row    " + username +"||||"+password);

        loginPage.fillAllData(username,password);
    }

    @When("the user clicks the Login button")
    public void clickOnTheLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("the home page should be displayed")
    public void checkIfLoginIsCorrectly() {
        loginPage.checkHomePageIsDisplayed();
    }

    @Then("a validation message should appear")
    public void checkIfErrorMessageIsAppear() {
        loginPage.checkIfErrorMessageIsAppears();
    }



}
