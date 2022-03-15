package com.mindr.pages.communitypages.leadershippage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class BatchEmailModal implements BasePage {
    private final MindrDriver driver;

    private final By sendButtonLocator = By.xpath("//button[contains(text(), 'Send')]");
    private final By subjectFieldLocator = By.id("email-subject");
    private final By messageFieldLocator = By.id("email-message");

    public BatchEmailModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(sendButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public LeadershipPage sendBatchEmail() {
        WebElement subjectField = driver.findElement(subjectFieldLocator);
        driver.setText(subjectField, "Batch Email Test Subject Line");

        WebElement messageField = driver.findElement(messageFieldLocator);
        driver.setText(messageField, "Batch Email Test Message");

        WebElement sendButton = driver.findElement(sendButtonLocator);
        driver.click(sendButton);

        return PageManager.getInstance().instantiateCurrentPage(LeadershipPage.class);
    }
}
