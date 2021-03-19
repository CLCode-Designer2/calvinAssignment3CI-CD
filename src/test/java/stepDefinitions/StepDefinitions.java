package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class StepDefinitions {

    private WebDriver driver;

    @Given("I have the browser open")
    public void iHaveTheBrowserOpen () throws InterruptedException {
        System.setProperty ("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver (); //Start Chrome
        //driver.manage().timeouts().implicitlyWait (60, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout (60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Thread.sleep (2000);

    }

    @And ("I have navigated to mailchimp")
    public void iHaveNavigatedToMailchimp() {
        driver.get("https://login.mailchimp.com/signup/"); //Navigate web service
        System.out.println ("Web service");
    }

    @When("^I enter \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterEmailAndUsernameAndPassword (String email, String username, String password)
            throws InterruptedException {
        Thread.sleep (2000);
        WebElement Email = driver.findElement (By.id ("email"));
        Email.sendKeys (email);
        WebElement Username = driver.findElement (By.id ("new_username"));
        Username.sendKeys (username);
        WebElement Password = driver.findElement (By.id ("new_password"));
        Password.sendKeys (password);
        System.out.println ("Email: " +email);
        System.out.println ("Username: " +username);
        System.out.println ("Password: " +password);

    }

    @And ("I press the sign up button")
    public void iPressTheSignUpButton () throws InterruptedException {
        Thread.sleep (2000);
        WebElement signUp = driver.findElement (By.xpath ("//*[@id=\"create-account\"]"));
        signUp.submit ();
        System.out.println ("Click");

    }

    @Then("I verify the sign up status")
    public void iVerifyTheSignUpStatus () throws InterruptedException {
        Thread.sleep (2000);
        //driver.get("https://login.mailchimp.com/signup/success/");
        WebElement h1 = driver.findElement (By.xpath ("//*[@id=\"signup-content\"]/div/div/div/span/div/p[2]/a"));
        System.out.println ("Status: " );
        assertEquals ("Re-enter your email and try again", h1.getText ());
        System.out.println ("Confirm & close app");
        driver.close ();

    }

}
