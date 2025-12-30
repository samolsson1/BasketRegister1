package stepdefs;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterSteps {

    WebDriver webDriver;

    @Given("att användaren öppnar registreringssidan")
    public void oppnar_registreringssidan() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

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

    @When("användaren anger sitt efternamn")
    public void fyll_i_efter() {
        webDriver.findElement(By.id("member_lastname")).sendKeys("Testsson");
    }

    @When("användaren skriver in e-post och bekräftar den")
    public void fyll_i_epost() {
        webDriver.findElement(By.id("member_emailaddress")).sendKeys("test@yahoo.com");
        webDriver.findElement(By.id("member_confirmemailaddress")).sendKeys("test@yahoo.com");
    }

    @When("användaren väljer ett lösenord och bekräftar det")
    public void fyll_i_losenord() {
        webDriver.findElement(By.id("signupunlicenced_password")).sendKeys("Lösenord123");
        webDriver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Lösenord123");
    }

    @When("användaren fyller i två olika lösenord")
    public void olika_losenord() {
        webDriver.findElement(By.id("signupunlicenced_password")).sendKeys("Lösenord123");
        webDriver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Lösenord1234");
    }

    @When("användaren godkänner villkoren")
    public void godkann_villkor() {
        webDriver.findElement(By.cssSelector("label[for='sign_up_25']")).click();
        webDriver.findElement(By.cssSelector("label[for='sign_up_26']")).click();
        webDriver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")).click();
    }

    @When("användaren skickar in formuläret")
    public void skicka_formular() {
        webDriver.findElement(By.cssSelector(".btn")).click();
    }

    @Then("ska ett konto skapas")
    public void konto_skapat() {
        WebElement tack = webDriver.findElement(By.cssSelector("h2.gray"));
        String resultat = tack.getText();
        Assert.assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", resultat);
        webDriver.quit();
    }

    @Then("ska ett felmeddelande om efternamn visas")
    public void fel_efternamn() {
        WebElement error = webDriver.findElement(By.cssSelector("span[generated='true']"));
        Assert.assertTrue(error.getText().contains("Last Name is required"));
        webDriver.quit();
    }

    @Then("ska ett felmeddelande om lösenord visas")
    public void fel_losenord() {
        WebElement error = webDriver.findElement(By.cssSelector("span[generated='true']"));
        Assert.assertTrue(error.getText().contains("Password did not match"));
        webDriver.quit();
    }

    @Then("ska ett felmeddelande om villkoren visas")
    public void fel_villkor() {
        WebElement error = webDriver.findElement(By.cssSelector("span[generated='true']"));
        Assert.assertTrue(error.getText().contains("Terms and Conditions"));
        webDriver.quit();
    }
}
