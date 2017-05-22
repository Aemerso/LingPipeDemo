package Utilities;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

/**
 * Created by Anthony Emerson on 5/22/2017.
 */
public class GetNewPoems {

    public static void main(String[] args) throws IOException {
        PageContainer pages;
        WebDriver driver;

        int poemsViewed = 0;

        driver = BuildWebDriver.buildChromeDriver();
        pages = new PageContainer(driver);
        driver.get(HomePage.URL);
        while (poemsViewed >= 0) {
            poemsViewed = pages.getHomePage().goToNextPoem(poemsViewed);
            if (poemsViewed == -1) {
                break;
            }
            pages.getPoemPage().getPoemText(poemsViewed);
            pages.getPoemPage().returnToHomePage();
        }
    }
}

