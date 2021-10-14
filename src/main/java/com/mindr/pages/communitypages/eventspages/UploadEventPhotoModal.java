package com.mindr.pages.communitypages.eventspages;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class UploadEventPhotoModal implements BasePage {
    private final MindrDriver driver;

    private final By closeModalLocator = By.xpath("//button[contains(., '×')]");
    private final By selectPhotoButtonLocator = By.xpath("/html/body/div[14]/div/div[3]/button[1]");

    public UploadEventPhotoModal(WebDriver driver) {
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

    public NewEventPage selectPhoto() {
        WebElement selectPhotoButton = driver
                .waitWithTimeout(ExpectedConditions.visibilityOfElementLocated(selectPhotoButtonLocator), 3);
        driver.click(selectPhotoButton);

        return PageManager.getInstance().instantiateCurrentPage(NewEventPage.class);
    }
}
