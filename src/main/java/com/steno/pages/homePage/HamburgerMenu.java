package com.steno.pages.homePage;

import com.steno.pages.talkToSalesPages.TalkToSalesForm;
import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.StenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HamburgerMenu implements BasePage {
    private final StenoDriver driver;

    private final By contactLinkLocator = By.xpath("//div[@class='mobile-cta'][@href^='/contact?hsLang=en']");

    public HamburgerMenu(WebDriver driver) {
        this.driver = new StenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(contactLinkLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoaded()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public TalkToSalesForm talkToSales() {
            WebElement contactLink = driver.wait(ExpectedConditions.elementToBeClickable(contactLinkLocator));
        driver.click(contactLink);

        throw new TestException("Failed to click the login button on mobile!!!");
    }
}