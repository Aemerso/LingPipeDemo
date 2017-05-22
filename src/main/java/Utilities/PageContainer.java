package Utilities;

import Pages.PoemPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by Anthony Emerson on 5/16/2017.
 */
public class PageContainer {

    private HomePage _homePage = null;
    private PoemPage _poemPage = null;
    private WebDriver _driver = null;

    public PageContainer(WebDriver driver){
        _driver = driver;
    }

    public HomePage getHomePage(){
        if (_homePage == null) _homePage = new HomePage(_driver);
        return _homePage;
    }
    public PoemPage getPoemPage(){
        _poemPage = new PoemPage(_driver);
        return _poemPage;
    }
}
