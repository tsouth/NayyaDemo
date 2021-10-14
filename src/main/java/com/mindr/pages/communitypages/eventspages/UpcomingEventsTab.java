package com.mindr.pages.communitypages.eventspages;

import com.mindr.pages.communitypages.callstoactionpages.ActiveCallsToActionTab;
import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import com.mindr.utilities.page.ModularURL;
import com.mindr.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;

public class UpcomingEventsTab implements BasePage, PageNavigation, ModularURL {
    private final MindrDriver driver;

    private String URL = "/ls/click%s";

    private final By upcomingEventsTitleLocator = By.xpath("//p[contains(., 'Upcoming Events')]");
    private final By newEventButtonLocator = By.xpath("//a[contains(., 'New Event')]");
    private final By successfulEventCreationBannerLocator = By.xpath("//div[@class='message']");
    private final By callsToActionMenuLocator = By.xpath("/html/body/aside/div[1]/nav/ul/li[2]/a");
    private final By communityTitleLocator = By.xpath("/html/body/aside/div[1]/div/div");

    public UpcomingEventsTab(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getSendGrindUrl() + URL);
    }

    @Override
    public void modifyURL(Object... urlIds) {
        URL = String.format(URL, urlIds);
    }

    public NewEventPage createNewEvent() {
        WebElement newEvent = driver.wait(ExpectedConditions.elementToBeClickable(newEventButtonLocator));
        driver.click(newEvent);

        return PageManager.getInstance().instantiateCurrentPage(NewEventPage.class);
    }

    public ActiveCallsToActionTab callsToAction() {
        WebElement callsToActionMenu = driver.wait(ExpectedConditions.elementToBeClickable(callsToActionMenuLocator));
        driver.click(callsToActionMenu);

        return PageManager.getInstance().instantiateCurrentPage(ActiveCallsToActionTab.class);
    }

    public UpcomingEventsTab verifyEventCreated() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(successfulEventCreationBannerLocator));

        return PageManager.getInstance().instantiateCurrentPage(UpcomingEventsTab.class);
    }

    public UpcomingEventsTab verifyCommunityEmailCreated() {
        String communityTitle = driver.wait(ExpectedConditions.visibilityOfElementLocated(communityTitleLocator)).getText();
        Assert.assertTrue(communityTitle.contains("Selenium Testing Community"));

        return PageManager.getInstance().instantiateCurrentPage(UpcomingEventsTab.class);
    }
}
