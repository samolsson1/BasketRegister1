package stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver createChrome() {
        return new ChromeDriver();
    }

    public static WebDriver createFirefox() {
        return new FirefoxDriver();
    }
}
