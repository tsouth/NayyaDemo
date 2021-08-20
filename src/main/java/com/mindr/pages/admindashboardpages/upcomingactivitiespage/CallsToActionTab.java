package com.mindr.pages.admindashboardpages.upcomingactivitiespage;

import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallsToActionTab implements BasePage {
    private final MindrDriver driver;

    private final By yourUpcomingCallsToActionTitleLocator = By.xpath("//p[contains(., 'Your Upcoming Calls to Action')]");

    public CallsToActionTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(yourUpcomingCallsToActionTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }
}
