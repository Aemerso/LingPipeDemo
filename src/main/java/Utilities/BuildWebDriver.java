package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Anthony Emerson on 5/16/2017.
 */
public class BuildWebDriver {

        private static String driverPath = "src/main/resources/";

        public static WebDriver buildChromeDriver() {
            System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
            return new ChromeDriver();
        }
        //create a FirefoxDriver object
        public static WebDriver buildFirefoxDriver() {
            System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
            return new FirefoxDriver();
        }

    }
