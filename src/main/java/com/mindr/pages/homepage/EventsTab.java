package com.mindr.pages.homepage;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class EventsTab implements BasePage {
    private final MindrDriver driver;

    private final By eventTitleLocator = By.xpath("//*[contains(text(), 'Selenium')]");
    private final By registerPlusButton = By.xpath("//div[@class='not-subscribed-content']");

    public EventsTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.elementToBeClickable(registerPlusButton));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public EventPage clickEventTile() {
        WebElement eventTileTitle = driver.wait(ExpectedConditions.elementToBeClickable(eventTitleLocator));
        driver.click(eventTileTitle);

        return PageManager.getInstance().instantiateCurrentPage(EventPage.class);
    }
}
