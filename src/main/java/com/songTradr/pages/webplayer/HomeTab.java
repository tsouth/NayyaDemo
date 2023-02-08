package com.songTradr.pages.webplayer;

import com.songTradr.utilities.managers.PageManager;
import com.songTradr.utilities.page.BasePage;
import com.songTradr.utilities.page.SongTradrDriver;
import com.songTradr.utilities.page.PageNavigation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HomeTab implements BasePage, PageNavigation {
    private final SongTradrDriver driver;

    private final By viewMyProfileLocator = By.cssSelector("a[href*='/dashboard/community_subscriptions']");
    private final By settingsLinkLocator = By.xpath("//a[contains(., 'Settings')]");
    private final By eventsTabLocator = By.xpath("//a[contains(., 'Events')]");
    private final By callsToActionTabLocator = By.xpath("//a[contains(., 'Calls to Action')]");
    private final By homeNavIconLocator = By.xpath("//a[@data-testid='navhomev2-link']");
    private final By searchNavIconLocator = By.xpath("//a[@data-testid='navsearchv2-link']");

    public HomeTab(WebDriver driver) {
        this.driver = new SongTradrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(homeNavIconLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getDemoUrl());
    }

    public SearchTab Search() {
        WebElement searchNavIcon = driver.wait(ExpectedConditions.elementToBeClickable(searchNavIconLocator));
        driver.click(searchNavIcon);

        return PageManager.getInstance().instantiateCurrentPage(SearchTab.class);
    }

}
