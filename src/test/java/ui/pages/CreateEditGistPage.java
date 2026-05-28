package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ui.config.WaitUtils;

public class CreateEditGistPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By descriptionField = By.name("gist[description]");
    private By filenameField = By.name("gist[contents][][name]");

    private By codeEditor = By.className("CodeMirror");
    private By submitButton = By.cssSelector("button.hx_create-pr-button");
    private By dropdownToggle = By.cssSelector("summary.select-menu-button");
    private By updateButton = By.xpath("//button[contains(text(), 'Update public gist')]");

    private By publicGistOption = By.xpath("//span[contains(@class, 'select-menu-item-heading') and contains(text(), 'Create public gist')]");

    public CreateEditGistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void fillGistForm(String description, String filename, String codeText) {
        wait.waitForVisibility(descriptionField).clear();
        wait.waitForVisibility(descriptionField).sendKeys(description);

        wait.waitForVisibility(filenameField).clear();
        wait.waitForVisibility(filenameField).sendKeys(filename);

        wait.waitForClickable(codeEditor).click();
        new Actions(driver).sendKeys(codeText).perform();
    }

    public void fillGistFormForEdit(String description, String codeText) {
        wait.waitForVisibility(descriptionField).clear();
        wait.waitForVisibility(descriptionField).sendKeys(description);

        wait.waitForClickable(codeEditor).click();
        new Actions(driver).sendKeys(codeText).perform();
    }

    public void submitPublicGist() {
        String currentBtnText = wait.waitForVisibility(submitButton).getText();

        if (currentBtnText.toLowerCase().contains("secret")) {
            wait.waitForClickable(dropdownToggle).click();
            wait.waitForClickable(publicGistOption).click();
        }

        wait.waitForClickable(submitButton).click();
    }

    public void submitEditGist() {
        wait.waitForClickable(updateButton).click();
    }
}