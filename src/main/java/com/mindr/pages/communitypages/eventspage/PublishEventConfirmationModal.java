package com.mindr.pages.communitypages.eventspage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class PublishEventConfirmationModal implements BasePage {
    private final MindrDriver driver;

    private final By publishButtonLocator = By.xpath("//button[contains(., 'Publish')]");

    public PublishEventConfirmationModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(publishButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public UpcomingEventsTab publishEvent() {
        WebElement publishButton = driver.wait(ExpectedConditions.elementToBeClickable(publishButtonLocator));
        driver.click(publishButton);

        return PageManager.getInstance().instantiateCurrentPage(UpcomingEventsTab.class);
    }
}
