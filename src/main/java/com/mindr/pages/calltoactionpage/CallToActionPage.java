package com.mindr.pages.calltoactionpage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import com.mindr.utilities.page.ModularURL;
import com.mindr.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallToActionPage implements BasePage, PageNavigation, ModularURL {
    private final MindrDriver driver;

    private String URL = "/ls/click%s";

    private final By markAsCompleteButtonLocator = By.xpath("//button[contains(., 'Mark As Complete')]");
    private final By markAsIncompleteButtonLocator = By.xpath("//button[contains(., 'Mark As Incomplete')]");
    private final By callToActionTitleLocator = By.xpath("//h1[contains(text(), 'Selenium Testing')]");

    public CallToActionPage(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(callToActionTitleLocator));
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

    public CallToActionCompletionModal markCallToActionAsComplete() {
        WebElement markAsCompleteButton = driver.wait(ExpectedConditions.elementToBeClickable(markAsCompleteButtonLocator));
        driver.click(markAsCompleteButton);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionCompletionModal.class);
    }

    public CallToActionIncompletionModal markCallToActionAsIncomplete() {
        WebElement markAsIncompleteButton = driver
                .wait(ExpectedConditions.elementToBeClickable(markAsIncompleteButtonLocator));
        driver.click(markAsIncompleteButton);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionIncompletionModal.class);
    }

    public CallToActionPage verifyCallToActionEmailCreated() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(callToActionTitleLocator));

        return PageManager.getInstance().instantiateCurrentPage(CallToActionPage.class);
    }

}
