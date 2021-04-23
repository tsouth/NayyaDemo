package com.mindr.pages.homepage;

import com.mindr.pages.communitiespage.ActiveCommunitiesTab;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;


public class MyDashboardTab implements BasePage {
    private final MindrDriver driver;

    private final By viewMyProfileLocator = By.xpath("//a[contains(., 'View My Profile')]");

    public MyDashboardTab (WebDriver driver) {
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

    public ActiveCommunitiesTab viewMyProfile () {
        WebElement viewMyProfile = driver.wait(ExpectedConditions.elementToBeClickable(viewMyProfileLocator));
        driver.click(viewMyProfile);

        return PageManager.getInstance().instantiateCurrentPage(ActiveCommunitiesTab.class);
    }
}
