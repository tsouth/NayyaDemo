package com.mindr.pages.communitypages.callstoactionpages;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class ActiveCallsToActionTab implements BasePage {
    private final MindrDriver driver;

    private final By activeCallsToActionTabLocator = By.xpath("//a[contains(., 'Active Calls to Action')]");
    private final By successfulCallToActionCreationBannerLocator = By.xpath("//div[@class='message']");
    private final By newCallToActionButtonLocator = By.xpath("//a[contains(., 'Create Call To Action')]");

    public ActiveCallsToActionTab(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(activeCallsToActionTabLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public NewCallToActionPage createNewCallToAction() {
        WebElement newCallToActionButton = driver.wait(ExpectedConditions.elementToBeClickable(newCallToActionButtonLocator));
        driver.click(newCallToActionButton);

        return PageManager.getInstance().instantiateCurrentPage(NewCallToActionPage.class);
    }

    public ActiveCallsToActionTab verifyCallToActionCreated() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(successfulCallToActionCreationBannerLocator));

        return PageManager.getInstance().instantiateCurrentPage(ActiveCallsToActionTab.class);
    }
}
