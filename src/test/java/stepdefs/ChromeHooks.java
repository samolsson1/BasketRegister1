package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class ChromeHooks {

    public static WebDriver webDriver;

    @Before
    public void setUp() {
        // BÖRJA med Chrome:
        webDriver = DriverFactory.createChrome();
        webDriver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}