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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class StepDefinitions {

    private WebDriver driver;
    private int len = 10;

    @Given("I have used {string} as a browser")
    public void iHaveUsedAsABrowser(String browser) {
        DriveCreator creator = new DriveCreator();
        driver = creator.createBrowser(browser);
    }

    @And ("I have navigated to mailchimp")
    public void iHaveNavigatedToMailchimp() {
        driver.get("https://login.mailchimp.com/signup/"); //Navigate web service
        driver.manage().window().maximize();
        driver.manage().timeouts ().pageLoadTimeout (15, TimeUnit.SECONDS); //Max (15) for page to load
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Max (30) seconds for all elements
        System.out.println ("Web service");
    }

    @When("^I enter \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterEmailAndUsernameAndPassword (String email, String username, String password)
            throws InterruptedException {

        WebElement Email = driver.findElement (By.cssSelector ("input[id = 'email']"));
        sendKeys (driver, Email, 3, email);

        WebElement Username = driver.findElement (By.cssSelector ("input[id = 'new_username']"));
        sendKeys (driver, Username, 3, new StringBuilder ().append (username).append (UserName (len)).toString ());

        WebElement Password = driver.findElement (By.cssSelector ("input[id = 'new_password']"));
        sendKeys (driver, Password, 3, password);

        System.out.println ("Email: " +email);
        System.out.println ("Username: " +username);
        System.out.println ("Password: " +password);

    }

    @And ("I press the sign up button")
    public void iPressTheSignUpButton () throws InterruptedException {
        // WebElement signUp = driver.findElement (By.cssSelector ("button[id = 'create-account']"));
        // signUp.submit ();

        click (driver, By.cssSelector ("button[id = 'create-account']"));
        System.out.println ("Click");

    }

    @Then("I verify the sign up status")
    public void iVerifyTheSignUpStatus () throws InterruptedException {

        WebElement h1 = driver.findElement (By.cssSelector ("h1[class = '!margin-bottom--lv3 no-transform center-on-medium']"));
        System.out.println ("Status: " );
        assertEquals ("Check your email", h1.getText ());
        System.out.println ("Confirm & close app");
        driver.close ();

    }

    public void click (WebDriver driver, By by) {

        new WebDriverWait (driver, 10).until (ExpectedConditions.elementToBeClickable (by));
        driver.findElement (by).submit ();
    }

    public void sendKeys (WebDriver driver, WebElement element, int timeout, String value) {

        new WebDriverWait (driver, 10).until (ExpectedConditions.visibilityOf (element));
        element.sendKeys (value);
    }

    static char[] UserName(int len)
    {
        // Creating a string variable for the random characters

        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String values = uppercase + lowercase + numbers;

        // Using random method
        Random rndm_method = new Random();
        // Creating a char array
        char[] new_username = new char[len];

        for (int i = 1; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() method : to transverse the array as int
            new_username[i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return new_username;
    }

}
