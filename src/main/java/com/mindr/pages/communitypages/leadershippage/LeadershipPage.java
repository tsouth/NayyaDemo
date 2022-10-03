package com.mindr.pages.communitypages.leadershippage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class LeadershipPage implements BasePage{
    private final MindrDriver driver;

    private final By batchEmailButtonLocator = By.xpath("//button[contains(text(), 'Batch Email')]");

    public LeadershipPage(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(batchEmailButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public BatchEmailModal createBatchEmail() {
        WebElement batchEmail = driver.wait(ExpectedConditions.elementToBeClickable(batchEmailButtonLocator));
        driver.click(batchEmail);

        return PageManager.getInstance().instantiateCurrentPage(BatchEmailModal.class);
    }
}
