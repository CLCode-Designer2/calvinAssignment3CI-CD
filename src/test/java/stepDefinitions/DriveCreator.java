package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriveCreator {

    public WebDriver createBrowser (String browser) {
        WebDriver driver;

        if(browser.equals ("chrome")) {
            System.setProperty ("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver ();
        } else {
            System.setProperty ("webdriver.gecko.driver", "geckodriver 2");
            driver = new FirefoxDriver ();
        }
        return driver;
    }

}
