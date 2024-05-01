package tests;

import browserFactory.DriverInit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SuiteSetup {


    public static WebDriver getDriver() {
        return DriverInit.getDriver(DriverInit.browserType);
    }

    @BeforeMethod
    public void launchBrowser() {
        DriverInit.getDriver("chrome");

    }

    @AfterMethod
    public void tearDownBrowser() {
        DriverInit.tearDown();

    }

}
