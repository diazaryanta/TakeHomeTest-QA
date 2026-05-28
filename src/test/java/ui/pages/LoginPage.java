package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.config.WaitUtils;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By signInHeaderLink = By.linkText("Sign in");
    private By usernameField = By.id("login_field");
    private By passwordField = By.id("password");
    private By loginButton = By.name("commit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickSignInHeader() {
        wait.waitForClickable(signInHeaderLink).click();
    }

    public void loginToGitHub(String username, String password) {
        wait.waitForVisibility(usernameField).sendKeys(username);
        wait.waitForVisibility(passwordField).sendKeys(password);
        wait.waitForClickable(loginButton).click();
    }
}