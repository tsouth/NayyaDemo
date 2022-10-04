package com.mindr.pages.loginpage;

import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.utilities.email.EmailCredentialUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import com.mindr.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.security.PrivilegedAction;
import java.util.Map;

public class LoginPage implements BasePage, PageNavigation {
    private final MindrDriver driver;
    private final EmailCredentialUtility emailCredentialUtility = new EmailCredentialUtility();

    private final String URL = "/auth/login";

    private final By emailTextFieldLocator = By.id("user_email");
    private final By passwordTextFieldLocator = By.id("user_password");
    private final By signInButtonLocator = By.xpath("//button[contains(., 'SIGN IN')]");
    private final By signInConfirmationBannerLocator = By.xpath("/html/body/div[2]/div");

    public LoginPage(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(signInButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getMindrUrl() + URL);
    }

    public MyDashboardTab signInAsAnAdmin() {
        signIn(emailCredentialUtility.getOrgAdminCredentials());
        WebElement signInConfirmationBanner = driver.wait(ExpectedConditions.presenceOfElementLocated(signInConfirmationBannerLocator));
        driver.wait(ExpectedConditions.invisibilityOf(signInConfirmationBanner));

        return PageManager.getInstance().instantiateCurrentPage(MyDashboardTab.class);
    }

    public MyDashboardTab signInAsAnEmployee() {
        signIn(emailCredentialUtility.getEmployeeCredentials());
        WebElement signInConfirmationBanner = driver.wait(ExpectedConditions.presenceOfElementLocated(signInConfirmationBannerLocator));
        driver.wait(ExpectedConditions.invisibilityOf(signInConfirmationBanner));

        return PageManager.getInstance().instantiateCurrentPage(MyDashboardTab.class);
    }

    private void signIn(Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        WebElement emailTextBox = driver.wait(ExpectedConditions.visibilityOfElementLocated(emailTextFieldLocator));
        driver.setText(emailTextBox, email);

        WebElement passwordTextBox = driver.wait(ExpectedConditions.visibilityOfElementLocated(
                passwordTextFieldLocator));
        driver.setText(passwordTextBox, password);

        WebElement signInButton = driver.wait(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        driver.click(signInButton);
    }
}
