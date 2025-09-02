package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = Hooks.driver;
    }

    public void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitForElementVisible(element);
        element.click();

    }

    public void sendTextByElement(WebElement element, String text) {
        waitForElementVisible(element);
        element.sendKeys(text);

    }

    public String getTextByElement(WebElement element) {
        waitForElementVisible(element);
        String val = element.getText();
        return val;
    }
    public boolean checkElementIsDisplayed(WebElement element) {
        waitForElementVisible(element);
        boolean val = element.isDisplayed();
        return val;
    }
}
