package com.brainpop.pages.signInPage;

import com.brainpop.utilities.email.EmailCredentialUtility;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import com.brainpop.utilities.page.ModularURL;
import com.brainpop.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.Map;

public class SignInPage implements BasePage, PageNavigation, ModularURL {
    private final BrainPopDriver driver;
    private final EmailCredentialUtility emailCredentialUtility = new EmailCredentialUtility();
    private String URL = "/login";


    private final By userNameTextFieldLocator = By.id("username");
    private final By passwordTextFieldLocator = By.id("password-input");
    private final By loginButtonLocator = By.id("login_button");

    public SignInPage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoaded()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getBrainPopUrl() + URL);
    }

    @Override
    public void modifyURL(Object... urlIds) {
        URL = String.format(URL, urlIds);
    }

    public CampaignsTab signInAsKid() {
        signIn(emailCredentialUtility.getAdminCredentials());
        return PageManager.getInstance().instantiateCurrentPage(CampaignsTab.class);
    }

    public CampaignsTab signInAsGrownUp() {
        signIn(emailCredentialUtility.getNYCBrokerCredentials());
        return PageManager.getInstance().instantiateCurrentPage(CampaignsTab.class);
    }

    private void signIn(Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        WebElement emailTextBox = driver.wait(ExpectedConditions.visibilityOfElementLocated(userNameTextFieldLocator));
        driver.setText(emailTextBox, email);

        WebElement passwordTextBox = driver.wait(ExpectedConditions.visibilityOfElementLocated(
                passwordTextFieldLocator));
        driver.setText(passwordTextBox, password);

        WebElement signInModalButton = driver.wait(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        driver.click(signInModalButton);
    }
}
