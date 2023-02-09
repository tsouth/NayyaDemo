package com.wellsaid.pages.WebStudio.SignInPage;

import com.wellsaid.pages.WebStudio.ProjectsPage.ProjectsPage;
import com.wellsaid.utilities.email.EmailCredentialUtility;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.PageNavigation;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.Map;

public class SignInPage implements BasePage, PageNavigation {
    private final WellSaidDriver driver;
    private final EmailCredentialUtility emailCredentialUtility = new EmailCredentialUtility();


    private final By signInButtonLocator = By.id("wsl-submit-button");
    private final By emailFormFieldLocator = By.id("1-email");
    private final By passwordFormFieldLocator = By.name("password");

    public SignInPage(WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(signInButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getWellSaidUrl());
    }

    public ProjectsPage signInAsTrialUser() {
        signIn(emailCredentialUtility.getTrialUserCredentials());
        return PageManager.getInstance().instantiateCurrentPage(ProjectsPage.class);
    }

    private void signIn(Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        WebElement emailFormField = driver.wait(ExpectedConditions.visibilityOfElementLocated(emailFormFieldLocator));
        driver.setText(emailFormField, email);

        WebElement passwordFormField = driver.wait(ExpectedConditions.visibilityOfElementLocated(
                passwordFormFieldLocator));
        driver.setText(passwordFormField, password);

        WebElement signInModalButton = driver.wait(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        driver.click(signInModalButton);
    }

}
