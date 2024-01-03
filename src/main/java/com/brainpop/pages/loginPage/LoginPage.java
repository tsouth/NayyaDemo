package com.brainpop.pages.loginPage;

import com.brainpop.pages.dashboardPage.DashboardPage;
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

public class LoginPage implements BasePage, PageNavigation, ModularURL {
    private final BrainPopDriver driver;
    private final EmailCredentialUtility emailCredentialUtility = new EmailCredentialUtility();
    private String URL = "/login";


    private final By userNameTextFieldLocator = By.id("username");
    private final By passwordTextFieldLocator = By.id("password-input");
    private final By loginButtonLocator = By.id("login_button");

    public LoginPage(WebDriver driver) {
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

    public DashboardPage loginWithKidCredentials() {
        login(emailCredentialUtility.getKidCredentials());
        return PageManager.getInstance().instantiateCurrentPage(DashboardPage.class);
    }

    private void login(Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        WebElement usernameTextBox = driver.wait(ExpectedConditions.visibilityOfElementLocated(userNameTextFieldLocator));
        driver.setText(usernameTextBox, username);

        WebElement passwordTextBox = driver.wait(ExpectedConditions.visibilityOfElementLocated(
                passwordTextFieldLocator));
        driver.setText(passwordTextBox, password);

        WebElement loginButton = driver.wait(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", loginButton);
        driver.click(loginButton);
    }
}
