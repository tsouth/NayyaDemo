package com.steno.pages.homePage;

import com.steno.pages.requestADemoPages.RequestADemoForm;
import com.steno.utilities.managers.PageManager;
import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.PageNavigation;
import com.steno.utilities.page.stenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HomePage implements BasePage, PageNavigation {
    private final stenoDriver driver;

    private final By loginButtonLocator = By.id("menu-item-6079");
    private final By bookADemoButtonLocator = By.id("header-nav-cta-btn");
    private final By hamburgerMenuLocator = By.cssSelector("//button[@class^='navbar-toggler']");


    public HomePage(WebDriver driver) {
        this.driver = new stenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(bookADemoButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getstenoUrl());
    }

    public RequestADemoForm requestADemo() {
        WebElement bookADemoButton = driver.wait(ExpectedConditions.elementToBeClickable(bookADemoButtonLocator));
        driver.click(bookADemoButton);

        return PageManager.getInstance().instantiateCurrentPage(RequestADemoForm.class);
    }


    public HamburgerMenu openHamburgerMenu() {
        WebElement hamburgerMenu = driver.wait(ExpectedConditions.elementToBeClickable(hamburgerMenuLocator));
        driver.click(hamburgerMenu);

        return PageManager.getInstance().instantiateCurrentPage(HamburgerMenu.class);
    }

}
