package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ui.config.WaitUtils;

public class GistDetailPage {
    private WebDriver driver;
    private WaitUtils wait;
    private By firstGistLink = By.cssSelector("strong.css-truncate-target");
    private By gistFilenameDisplay = By.cssSelector("strong.gist-blob-name");
    private By editButton = By.cssSelector("a.Button--secondary.Button--small.Button");
    private By deleteButton = By.xpath("//button[contains(@class, 'Button--danger')]");

    public GistDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickFirstGistFromList() {
        wait.waitForClickable(firstGistLink).click();
    }

    public String getGistFilename() {
        return wait.waitForVisibility(gistFilenameDisplay).getText();
    }

    public void clickEditGist() {
        org.openqa.selenium.WebElement element = wait.waitForVisibility(editButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickDeleteGist() {
        wait.waitForClickable(deleteButton).click();
        driver.switchTo().alert().accept();
    }
}