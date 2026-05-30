package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.base.BaseUITest;
import ui.pages.CreateEditGistPage;
import ui.pages.GistDetailPage;
import ui.pages.GistHomePage;
import ui.pages.LoginPage;
import utils.ConfigReader;
import utils.TestListenerUI;

@Listeners(TestListenerUI.class)
public class GistCRUDTest extends BaseUITest {

    private String testFilename = "TestQA_Automation_" + System.currentTimeMillis() + ".txt";

    @Test(description = "Pre-Condition: Verify successful login to GitHub Gist", priority = 1)
    public void testLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        GistHomePage homePage = new GistHomePage(driver);

        String username = ConfigReader.getProperty("github.username");
        String password = ConfigReader.getProperty("github.password");

        loginPage.clickSignInHeader();
        loginPage.loginToGitHub(username, password);

        Assert.assertTrue(homePage.isUserLoggedIn(), "FAILED: Login gagal!");
    }

    @Test(description = "Test Case 1: Create Public Gist", priority = 2, dependsOnMethods = "testLoginSuccessfully")
    public void testCreateGist() {
        GistHomePage homePage = new GistHomePage(driver);
        CreateEditGistPage editorPage = new CreateEditGistPage(driver);
        GistDetailPage detailPage = new GistDetailPage(driver);

        homePage.clickCreateNewGist();
        editorPage.fillGistForm("Automation Test Gist", testFilename, "Hello QA!");
        editorPage.submitPublicGist();

        Assert.assertEquals(detailPage.getGistFilename(), testFilename, "Gagal menemukan nama file Gist baru!");
    }

    @Test(description = "Test Case 2: Edit Existing Gist", priority = 3, dependsOnMethods = "testCreateGist")
    public void testEditGist() {
        GistDetailPage detailPage = new GistDetailPage(driver);
        CreateEditGistPage editorPage = new CreateEditGistPage(driver);
        String username = ConfigReader.getProperty("github.username");

        driver.navigate().to("https://gist.github.com/" + username);
        detailPage.clickFirstGistFromList();
        detailPage.clickEditGist();

        editorPage.fillGistFormForEdit("Automation Test Gist Update", " // edited code modification");
        editorPage.submitEditGist();

        Assert.assertEquals(detailPage.getGistFilename(), testFilename, "Proses Update Gist gagal!");
    }

    // Di buka dulu
    /*@Test(description = "Test Case 3: Delete Existing Gist", priority = 4, dependsOnMethods = "testEditGist")
    public void testDeleteGist() {
        GistDetailPage detailPage = new GistDetailPage(driver);

        detailPage.clickDeleteGist();
    }*/

    @Test(description = "Test Case 4: See List of Gists", priority = 5, dependsOnMethods = "testDeleteGist")
    public void testSeeListOfGists() {
        String username = ConfigReader.getProperty("github.username");

        driver.navigate().to("https://gist.github.com/" + username);
        Assert.assertTrue(driver.getCurrentUrl().contains(username), "Gagal kembali ke halaman List of Gists!");

        boolean isDeletedGistStillVisible = driver.getPageSource().contains(testFilename);
        Assert.assertFalse(isDeletedGistStillVisible, "Gagal! File Gist yang dihapus masih muncul di daftar List!");
    }
}