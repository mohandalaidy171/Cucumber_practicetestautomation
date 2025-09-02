package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ExcelReader;

import java.util.Map;

public class Hooks {


    public static WebDriver driver;

    public  Map<String, String> testData = ExcelReader.loadKeyValueData("src/test/resources/testData.xlsx",  "configration");
    String urlFromExcel = ExcelReader.getCellData(0, 1);
    DriverFactory driverFactory = new DriverFactory();

    @Before
    public void setupDriver() {


        driver = driverFactory.initDriver();
        driver.get(urlFromExcel);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            if (driver instanceof TakesScreenshot) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Screenshot");
            }

            scenario.log("Scenario failed: " + scenario.getName());
            scenario.log("Status: " + scenario.getStatus());
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
