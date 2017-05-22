package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Anthony Emerson on 5/16/2017.
 */
public class HomePage {
    private WebDriver _driver;
    public static String URL = "http://www.public-domain-poetry.com/";

    public HomePage(WebDriver driver){
        _driver = driver;
    }

    public int getNumOfPoems(){
        return _driver.findElements(By.cssSelector("#wrap > table:nth-child(3) > tbody > tr > td:nth-child(2) >" +
                " table:nth-child(13) > tbody > tr")).size();
    }
    public int goToNextPoem(int numOfPoemsViewed) {
        int numOfPoems = getNumOfPoems();
        int currentTableElement = numOfPoemsViewed + 2;
        if (currentTableElement > numOfPoems) {
            _driver.quit();
            return numOfPoemsViewed = -1;
        } else {
            numOfPoemsViewed++;
            _driver.findElement(By.cssSelector("#wrap > table:nth-child(3) > tbody > tr > td:nth-child(2) >" +
                    " table:nth-child(13) > tbody > tr:nth-child(" + currentTableElement + ") > td:nth-child(1)" +
                    " > font > a")).click();
            return numOfPoemsViewed;
        }
    }
}
