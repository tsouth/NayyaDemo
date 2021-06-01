package com.mindr.pages.communitypage;

import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CommunityEventsPage implements BasePage {
    private final MindrDriver driver;

    private final By upcomingEventsTabLocator = By.xpath("//a[contains(., 'Upcoming Events')]");

    public CommunityEventsPage (WebDriver driver) {
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

}
