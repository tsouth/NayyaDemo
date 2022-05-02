package com.mindr.pages.homepage;

import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.admindashboardpages.communitysubscriptionspage.CommunitySubscriptionsPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v84.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class MyDashboardTab implements BasePage {
    private final MindrDriver driver;

    private final By viewMyProfileLocator = By.cssSelector("a[href*='/dashboard/community_subscriptions']");
    private final By organizationSettingsLinkLocator = By.xpath("//a[contains(., 'Organization Settings')]");
    private final By eventsTabLocator = By.xpath("//a[contains(., 'Events')]");
    private final By callsToActionTabLocator = By.xpath("//a[contains(., 'Calls to Action')]");

    public MyDashboardTab(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(viewMyProfileLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public ActiveCommunitiesTab organizationSettings() {
        WebElement organizationSettings = driver.wait(ExpectedConditions.elementToBeClickable(organizationSettingsLinkLocator));
        driver.click(organizationSettings);

        return PageManager.getInstance().instantiateCurrentPage(ActiveCommunitiesTab.class);
    }

    public CommunitySubscriptionsPage viewMyProfileAsAnAdmin() {
        WebElement viewMyProfile = driver.wait(ExpectedConditions.elementToBeClickable(viewMyProfileLocator));
        driver.click(viewMyProfile);

        return PageManager.getInstance().instantiateCurrentPage(CommunitySubscriptionsPage.class);
    }

    public CommunitySubscriptionsPage viewMyProfileAsAnEmployee() {
        WebElement viewMyProfile = driver.wait(ExpectedConditions.elementToBeClickable(viewMyProfileLocator));
        driver.click(viewMyProfile);

        return PageManager.getInstance().instantiateCurrentPage(CommunitySubscriptionsPage.class);
    }

    public EventsTab events() {
        WebElement eventsTab = driver.wait(ExpectedConditions.elementToBeClickable(eventsTabLocator));
        driver.click(eventsTab);

        return PageManager.getInstance().instantiateCurrentPage(EventsTab.class);
    }

    public CallsToActionTab callsToAction() {
        WebElement callsToActionTab = driver.wait(ExpectedConditions.elementToBeClickable(callsToActionTabLocator));
        driver.click(callsToActionTab);

        return PageManager.getInstance().instantiateCurrentPage(CallsToActionTab.class);
    }
}
