package com.mindr.pages.homepage;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.List;
import java.util.Random;

public class EventsTab implements BasePage {
    private final MindrDriver driver;

    private final By eventTitleLocator = By.xpath("//*[contains(text(), 'Selenium Testing Event')]");
    private final By registerPlusButton = By.xpath("//div[@class='not-subscribed-content']");

    public EventsTab(WebDriver driver) {
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
        List<WebElement> eventTiles = driver.wait(ExpectedConditions.presenceOfAllElementsLocatedBy(eventTitleLocator));
        WebElement event = driver.wait(ExpectedConditions.elementToBeClickable(
                eventTiles.get(new Random().nextInt(eventTiles.size()))));
        driver.click(event);

        return PageManager.getInstance().instantiateCurrentPage(EventPage.class);
    }
}
