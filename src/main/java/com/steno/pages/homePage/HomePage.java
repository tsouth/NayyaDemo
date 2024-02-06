package com.steno.pages.homePage;

import com.steno.pages.talkToSalesPages.TalkToSalesForm;
import com.steno.utilities.managers.PageManager;
import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.PageNavigation;
import com.steno.utilities.page.StenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HomePage implements BasePage, PageNavigation {
    private final StenoDriver driver;

    private final By learMoreAboutStenoButtonLocator = By.xpath("//a[contains(text(), 'Learn More About Steno')]");
    private final By hamburgerMenuLocator = By.id("mobile-toggle");


    public HomePage(WebDriver driver) {
        this.driver = new StenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(learMoreAboutStenoButtonLocator));
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

    public TalkToSalesForm talkToSales() {
        WebElement learMoreAboutStenoButton = driver.wait(ExpectedConditions.elementToBeClickable(learMoreAboutStenoButtonLocator));
        driver.click(learMoreAboutStenoButton);

        return PageManager.getInstance().instantiateCurrentPage(TalkToSalesForm.class);
    }


    public com.steno.pages.homePage.HamburgerMenu openHamburgerMenu() {
        WebElement hamburgerMenu = driver.wait(ExpectedConditions.elementToBeClickable(hamburgerMenuLocator));
        driver.click(hamburgerMenu);

        return PageManager.getInstance().instantiateCurrentPage(com.steno.pages.homePage.HamburgerMenu.class);
    }

}
