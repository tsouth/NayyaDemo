package com.mindr.pages.communitypages.callstoactionpages;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class PublishCallToActionConfirmationModal implements BasePage {
    private final MindrDriver driver;

    private final By closeModalLocator = By.xpath("//button[contains(., '×')]");
    private final By publishButtonLocator = By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show" +
            " > div > div.swal2-actions.modal.actions > button.swal2-confirm.button.primary");

    public PublishCallToActionConfirmationModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(closeModalLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public ActiveCallsToActionTab publishCallToAction() {
        WebElement publishButton = driver.findElement(publishButtonLocator);
        driver.click(publishButton);

        return PageManager.getInstance().instantiateCurrentPage(ActiveCallsToActionTab.class);
    }
}
