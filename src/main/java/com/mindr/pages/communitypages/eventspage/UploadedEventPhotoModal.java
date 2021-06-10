package com.mindr.pages.communitypages.eventspage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class UploadedEventPhotoModal implements BasePage {
    private final MindrDriver driver;

    private final By modalHeadingLocator = By.xpath("//h2[contains(., 'Event Photo')]");
    private final By selectPhotoButtonLocator = By.xpath("//button[contains(., 'Select')]");

    public UploadedEventPhotoModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(modalHeadingLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public NewEventPage selectPhoto() {
        WebElement selectPhotoButton = driver.wait(ExpectedConditions.visibilityOfElementLocated(selectPhotoButtonLocator));
        driver.click(selectPhotoButton);

        return PageManager.getInstance().instantiateCurrentPage(NewEventPage.class);
    }
}
