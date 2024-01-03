package com.brainpop.pages.homePage;

import com.brainpop.pages.loginPage.LoginPage;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.PageNavigation;
import com.brainpop.utilities.page.BrainPopDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HomePage implements BasePage, PageNavigation {
    private final BrainPopDriver driver;

    private final By loginButtonLocator = By.id("nli_login_button__BV_toggle_");
    private final By hamburgerMenuLocator = By.id("hamburger-menu");
    private final By kidButtonLocator = By.id("nli_kid_button");


    public HomePage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getBrainPopUrl());
    }

    public LoginPage loginAsAKid() {
        WebElement loginButton = driver.wait(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        driver.click(loginButton);

        WebElement kidButton = driver.wait(ExpectedConditions.elementToBeClickable(kidButtonLocator));
        driver.click(kidButton);

        return PageManager.getInstance().instantiateCurrentPage(LoginPage.class);
    }


    public HamburgerMenu openHamburgerMenu() {
        WebElement hamburgerMenu = driver.wait(ExpectedConditions.elementToBeClickable(hamburgerMenuLocator));
        driver.click(hamburgerMenu);

        return PageManager.getInstance().instantiateCurrentPage(HamburgerMenu.class);
    }

}
