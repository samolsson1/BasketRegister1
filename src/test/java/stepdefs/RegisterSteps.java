package stepdefs;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterSteps {

    // Använder samma WebDriver som skapas i ChromeHooks
    WebDriver webDriver = ChromeHooks.webDriver;

    // Privat metod med explicit wait
    private WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Given("att användaren öppnar registreringssidan")
    public void oppnar_registreringssidan() {
        // ÄNDRA denna sökväg om din Register.html ligger någon annanstans
        String path = "file:///Users/sam.olsson2/Downloads/Register/Register.html";
        webDriver.get(path);
    }

    @When("användaren fyller i giltig födelsedag")
    public void fyll_i_fodelsedag() {
        webDriver.findElement(By.id("dp")).sendKeys("01/01/1928");
    }

    @When("användaren anger sitt förnamn")
    public void fyll_i_for() {
        webDriver.findElement(By.id("member_firstname")).sendKeys("Sam");
    }

    // styr om efternamn ska fyllas i eller inte (Scenario Outline-kolumnen "efternamn")
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

    // parametriserade lösenord (kolumnerna "losenord" och "bekraftaLosenord")
    @When("användaren fyller i lösenord {string} och bekräftar {string}")
    public void fyll_i_param_losenord(String losenord, String bekrafta) {
        webDriver.findElement(By.id("signupunlicenced_password")).sendKeys(losenord);
        webDriver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(bekrafta);
    }

    // styr om villkoren ska godkännas eller inte (kolumnen "villkor")
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

    // använder explicit wait för att läsa ut resultattexten
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
    }
}


