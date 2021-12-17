package com.mindr.pages.admindashboardpages.communitymanagementpage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class UploadPhotoModal implements BasePage {
    private final MindrDriver driver;

    private final By selectPhotoButtonLocator = By.xpath("//button[contains(text(), 'Select')]");

    public UploadPhotoModal(WebDriver driver) {this.driver = new MindrDriver(driver); }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(selectPhotoButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public NewCommunityPage selectPhoto() {
        WebElement selectPhotoButton = driver.findElement(selectPhotoButtonLocator);
        driver.click(selectPhotoButton);

        return PageManager.getInstance().instantiateCurrentPage(NewCommunityPage.class);
    }
}
