package stepdefs;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterSteps {


    //WebDriver webDriver = new ChromeDriver();
    WebDriver webDriver = new org.openqa.selenium.firefox.FirefoxDriver();

    private WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Given("att användaren öppnar registreringssidan")
    public void oppnar_registreringssidan() {
        String path = "file:///C:/Users/samol/Downloads/Register/Register.html"; // ändra vid behov
        webDriver.get(path);
        webDriver.manage().window().maximize();
    }

    @When("användaren fyller i giltig födelsedag")
    public void fyll_i_fodelsedag() {
        webDriver.findElement(By.id("dp")).sendKeys("01/01/1928");
    }

    @When("användaren anger sitt förnamn")
    public void fyll_i_for() {
        webDriver.findElement(By.id("member_firstname")).sendKeys("Sam");
    }

    @When("användaren anger sitt efternamn {string}")
    public void fyll_i_efternamn_param(String efternamnFlagga) {
        WebElement lastName = webDriver.findElement(By.id("member_lastname"));
        if (!"saknas".equals(efternamnFlagga)) {
            lastName.sendKeys("Testsson");
        }
    }

    @When("användaren skriver in e-post och bekräftar den")
    public void fyll_i_epost() {
        webDriver.findElement(By.id("member_emailaddress")).sendKeys("test@yahoo.com");
        webDriver.findElement(By.id("member_confirmemailaddress")).sendKeys("test@yahoo.com");
    }

    @When("användaren fyller i lösenord {string} och bekräftar {string}")
    public void fyll_i_param_losenord(String losenord, String bekrafta) {
        webDriver.findElement(By.id("signupunlicenced_password")).sendKeys(losenord);
        webDriver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(bekrafta);
    }

    @When("användaren godkänner villkoren {string}")
    public void godkann_villkor_param(String villkorFlagga) {
        if ("ja".equals(villkorFlagga)) {
            webDriver.findElement(By.cssSelector("label[for='sign_up_25']")).click();
            webDriver.findElement(By.cssSelector("label[for='sign_up_26']")).click();
            webDriver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")).click();
        }
    }

    @When("användaren skickar in formuläret")
    public void skicka_formular() {
        webDriver.findElement(By.cssSelector(".btn")).click();
    }

    @Then("ska resultatet vara {string}")
    public void kontrollera_meddelande(String meddelande) {

        if ("OK".equals(meddelande)) {
            WebElement tack = waitForVisible(By.cssSelector("h2.gray"));
            String resultat = tack.getText();
            Assert.assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", resultat);
        } else {
            WebElement error = waitForVisible(By.cssSelector("span[generated='true']"));
            Assert.assertTrue(error.getText().contains(meddelande));
        }

        webDriver.quit(); // stäng browsern efter varje scenario
    }
}
