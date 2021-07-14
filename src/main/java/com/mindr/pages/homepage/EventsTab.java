package com.mindr.pages.homepage;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class EventsTab implements BasePage {
    private final MindrDriver driver;

    private final By eventsTitleLocator = By.xpath("//p[contains(., 'Next Event')]");
    private final By testEventTileLocator = By.xpath("//*[contains(., 'Selenium Testing')]");

    public EventsTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(eventsTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public EventPage clickEventTile() {
        WebElement eventTile = driver.wait(ExpectedConditions.elementToBeClickable(testEventTileLocator));
        driver.click(eventTile);

        return PageManager.getInstance().instantiateCurrentPage(EventPage.class);
    }
}
