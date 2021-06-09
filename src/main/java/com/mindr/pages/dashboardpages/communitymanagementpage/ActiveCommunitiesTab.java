package com.mindr.pages.dashboardpages.communitymanagementpage;

import com.mindr.pages.communitypages.eventspage.UpcomingEventsTab;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class ActiveCommunitiesTab implements BasePage {
    private final MindrDriver driver;

    private final By communityLocator = By.xpath("//div[contains(.,'QA Community for Automated Testing')]");

    public ActiveCommunitiesTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(communityLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public UpcomingEventsTab selectActiveCommunity() {
        WebElement community = driver.wait(ExpectedConditions.elementToBeClickable(communityLocator));
        driver.click(community);

        return PageManager.getInstance().instantiateCurrentPage(UpcomingEventsTab.class);
    }
}
