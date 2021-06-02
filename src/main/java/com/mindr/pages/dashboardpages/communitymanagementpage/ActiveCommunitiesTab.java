package com.mindr.pages.dashboardpages.communitymanagementpage;

import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class ActiveCommunitiesTab implements BasePage {
    private final MindrDriver driver;

    private final By campaignLocator = By.xpath("//div[contains(.,'QA Community for Automated Testing')]");

    public ActiveCommunitiesTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(campaignLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }
}
