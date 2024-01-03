package com.brainpop.pages.homePage;

import com.brainpop.pages.loginPage.LoginPage;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HamburgerMenu implements BasePage {
    private final BrainPopDriver driver;

    private final By loginButtonLocator = By.id("m_nli_login_button__BV_toggle_");
    private final By kidButtonLocator = By.id("nli_kid_button");

    public HamburgerMenu(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(loginButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoaded()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public LoginPage loginAsAKid() {
        for (int i = 0; i < 5; i++) {
            try {
                WebElement loginButton = driver.wait(ExpectedConditions.elementToBeClickable(loginButtonLocator));
                driver.click(loginButton);

                WebElement kidButton = driver.wait(ExpectedConditions.elementToBeClickable(kidButtonLocator));
                driver.click(kidButton);

                return PageManager.getInstance().instantiateCurrentPage(LoginPage.class);
            } catch (WebDriverException ignore) {
            }
        }

        throw new TestException("Failed to click the login button on mobile!!!");
    }
}