package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.config.WaitUtils;

public class GistHomePage {
    private WebDriver driver;
    private WaitUtils wait;
    private By createGistIcon = By.id("gists-header-new-gist");

    public GistHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public boolean isUserLoggedIn() {
        return wait.waitForVisibility(createGistIcon).isDisplayed();
    }

    public void clickCreateNewGist() {
        wait.waitForClickable(createGistIcon).click();
    }
}