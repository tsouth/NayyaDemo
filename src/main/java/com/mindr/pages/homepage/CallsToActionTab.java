package com.mindr.pages.homepage;

import com.mindr.pages.calltoactionpage.CallToActionPage;
import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallsToActionTab implements BasePage {
    private final MindrDriver driver;

    private final By callsToActionTitleLocator = By.xpath("//p[contains(., 'Upcoming Call to Action')]");
    private final By automationTestCallToActionTileLocator = By.xpath("//h2[contains(., 'Automation Test Call to Action')]");

    public CallsToActionTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(callsToActionTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public CallToActionPage clickCallToActionTile() {
        WebElement callToActionTile = driver.wait(ExpectedConditions.elementToBeClickable(automationTestCallToActionTileLocator));
        driver.click(callToActionTile);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionPage.class);
    }
}
