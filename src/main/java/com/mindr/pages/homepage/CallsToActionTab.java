package com.mindr.pages.homepage;

import com.mindr.pages.calltoactionpage.CallToActionPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.List;
import java.util.Random;

public class CallsToActionTab implements BasePage {
    private final MindrDriver driver;

    private final By callToActionTitleLocator = By.xpath("//*[contains(text(), 'Selenium Testing Call')]");
    private final By registerTickButton = By.xpath("//div[contains(@class, 'not-subscribed-content')]");

    public CallsToActionTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(registerTickButton));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public CallToActionPage clickCallToActionTile() {
        List<WebElement> callToActionTiles = driver.wait(ExpectedConditions.presenceOfAllElementsLocatedBy(callToActionTitleLocator));
        WebElement callToAction = driver.wait(ExpectedConditions.elementToBeClickable(
                callToActionTiles.get(new Random().nextInt(callToActionTiles.size()))));
        driver.click(callToAction);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionPage.class);
    }
}
