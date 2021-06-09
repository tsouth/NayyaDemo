package com.mindr.pages.communitypages.eventspage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class UpcomingEventsTab implements BasePage {
    private final MindrDriver driver;

    private final By upcomingEventsTabLocator = By.xpath("//a[contains(., 'Upcoming Events')]");
    private final By newEventButtonLocator = By.xpath("//a[contains(., 'New Event')]");

    public UpcomingEventsTab(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTabLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public NewEventPage createNewEvent() {
        WebElement newEvent = driver.wait(ExpectedConditions.elementToBeClickable(newEventButtonLocator));
        driver.click(newEvent);

        return PageManager.getInstance().instantiateCurrentPage(NewEventPage.class);
    }
}
