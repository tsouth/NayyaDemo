package com.steno.pages.homePage;

import com.steno.pages.requestADemoPages.RequestADemoForm;
import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.stenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HamburgerMenu implements BasePage {
    private final stenoDriver driver;

    private final By bookADemoButtonLocator = By.id("header-nav-cta-btn");

    public HamburgerMenu(WebDriver driver) {
        this.driver = new stenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(bookADemoButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoaded()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public RequestADemoForm requestADemo() {
        WebElement bookADemoButton = driver.wait(ExpectedConditions.elementToBeClickable(bookADemoButtonLocator));
        driver.click(bookADemoButton);

        throw new TestException("Failed to click the login button on mobile!!!");
    }
}