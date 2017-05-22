package Pages;

import Utilities.PoemToFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

/**
 * Created by Anthony Emerson on 5/16/2017.
 */
public class PoemPage {
    private WebDriver _driver;
    private PoemToFile saveFile = new PoemToFile();

    public PoemPage(WebDriver driver){
        _driver = driver;
    }

    public void returnToHomePage(){
        _driver.findElement(By.cssSelector("#wrap > table:nth-child(3) > tbody > tr > td:nth-child(1) > table >" +
                " tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td >" +
                " font > a")).click();
    }
    public void getPoemText(int numPoemsViewed) throws FileNotFoundException {
        String text = "";
        text += _driver.findElement(By.cssSelector("#wrap > table:nth-child(3) > tbody > tr > td:nth-child(2) >" +
                "center > div:nth-child(1) > center > table > tbody > tr > td > table > tbody > tr > td >" +
                " font.t3a")).getText();
        saveFile.savePoem(text, numPoemsViewed);
    }
}