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

    @Given("I have used {string} as a browser")
    public void iHaveUsedAsABrowser(String browser) {
        DriveCreator creator = new DriveCreator();
        driver = creator.createBrowser(browser); //Browser choice can be made from Cucumber feature file
    }

    @And ("I have navigated to mailchimp")
    public void iHaveNavigatedToMailchimp() {
        driver.get("https://login.mailchimp.com/signup/"); //Navigate web service
        driver.manage().window().maximize(); //Driver function to maximize window size
        driver.manage().timeouts ().pageLoadTimeout (15, TimeUnit.SECONDS); //Max (15) for page to load
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Max (30) seconds for all elements
        System.out.println ("Web service");
    }

    @When("^I enter \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterEmailAndUsernameAndPassword (String email, String username, String password) {

        WebElement Email = driver.findElement (By.cssSelector ("input[id = 'email']"));

        if (email.equalsIgnoreCase("A")) { //if condition to determine email input
            sendKeys (driver, Email, 3, "alcalgone@yahoo.com"); //Implicit 3 second wait to send keys
        } else if (email.equalsIgnoreCase("B")) {
            Random idGenerator = new Random(); //Random email id# generator
            int randomInt = idGenerator.nextInt(1000); //One thousand random id#'s generated
            sendKeys(driver, Email, 3,"tester" + randomInt +"@yahoo.com"); //Implicit 3 second wait
        } else {
            sendKeys (driver, Email, 3, " "); //Implicit 3 second wait to send keys
        }
        WebElement Username = driver.findElement (By.cssSelector ("input[id = 'new_username']"));

        if (username.equalsIgnoreCase ("OVER")) { //if condition to determine username input
            username = new StringBuilder ().append (UserName (101)).toString (); //Random username generator
            sendKeys (driver, Username, 3, username); //Implicit 3 second wait to send keys
        } else if (username.equalsIgnoreCase ("REPEAT")) {
            username = "Blickson";
            sendKeys (driver, Username, 3, username); //Implicit 3 second wait to send keys
        } else {
            username = new StringBuilder ().append (UserName (10)).toString (); //Random username generator
            sendKeys (driver, Username, 3, username); //Implicit 3 second wait to send keys
        }
        WebElement Password = driver.findElement (By.cssSelector ("input[id = 'new_password']"));
        sendKeys (driver, Password, 3, password); //Implicit 3 second wait to send keys

        System.out.println ("Email: " +email);
        System.out.println ("Username: " +username);
        System.out.println ("Password: " +password);
    }

    @And ("I press the sign up button")
    public void iPressTheSignUpButton () {
        click (driver, By.cssSelector ("button[id = 'create-account']")); //Implicit 5 second wait for button click
        System.out.println ("Click");
    }

    @Then("I verify the sign up {string}")
    public void iVerifyTheSignUpStatus (String status) throws InterruptedException {
        System.out.println ("Begin Test-> " );

        boolean flag = false;
        //if condition to determine test return status
        if (status.equalsIgnoreCase ("BLANK")) {
            flag = true;
            WebElement eMessage = driver.findElement (By.cssSelector ("span[class = 'invalid-error']"));

            assertEquals("Please enter a value", eMessage.getText());
            System.out.println("Blank email");
        }
        else if (status.equalsIgnoreCase ("USED")) {
            flag = true;
            WebElement eMessage = driver.findElement (By.cssSelector ("span[class = 'invalid-error']"));
            assertEquals("Another user with this username already exists. Maybe it's your evil twin. Spooky.", eMessage.getText());
            System.out.println("User already exist");
        }
        else if (status.equalsIgnoreCase ("LONG")) {
            flag = true;
            WebElement eMessage = driver.findElement (By.cssSelector ("span[class = 'invalid-error']"));
            assertEquals("Enter a value less than 100 characters long",eMessage.getText());
            System.out.println("User name over 100 characters long");
        }
        else if (status.equalsIgnoreCase ("SUCCESS")){
            flag = true;
            WebElement h1 = driver.findElement (By.cssSelector ("h1[class = '!margin-bottom--lv3 no-transform center-on-medium']"));
            assertEquals ("Check your email", h1.getText ());
            System.out.println("Your password is secure and you're all set!");
        }
        System.out.println ("<-End Test");
        driver.close ();
        driver.quit ();
    }

    public void click (WebDriver driver, By by) {
        //implicit (5) second wait method for clickable element
        new WebDriverWait (driver, 5).until (ExpectedConditions.elementToBeClickable (by));
        driver.findElement (by).submit ();
    }

    public void sendKeys (WebDriver driver, WebElement element, int timeout, String value) {
        //implicit (3) second wait method for visibility of element
        new WebDriverWait (driver, 3).until (ExpectedConditions.visibilityOf (element));
        element.sendKeys (value);
    }

    static char[] UserName(int len)
    {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Uppercase string
        String lowercase = "abcdefghijklmnopqrstuvwxyz"; //lowercase string
        String numbers = "0123456789"; //Number string
        String values = uppercase + lowercase + numbers; //Assign character strings to (1) variable

        Random rndm_method = new Random(); //Create random method
        char[] new_username = new char[len]; //Create char array
        for (int i = 0; i < len; i++) //Create for loop that increments until it reaches the value of [len]
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() method : to transverse the array as integers
            new_username[i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return new_username; //Return a random character string
    }
}
