package com.mindr.pages.profilepage;

import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class EditProfilePage implements BasePage {
    private final MindrDriver driver;

    private final By editProfileLocator = By.xpath("//h2[contains(., 'Edit Profile')]");
    private final By firstNameTextFieldLocator = By.id("user_first_name");
    private final By saveButtonLocator = By.xpath("//input[@value='Save']");
    private final By saveProfileConfirmationBannerLocator = By.xpath("//div[contains(.,'Your profile was saved successfully.')]");

    public EditProfilePage (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(editProfileLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public MyDashboardTab setProfileFirstName(String firstName) {
        WebElement firstNameTextField = driver.wait(ExpectedConditions.presenceOfElementLocated(
                firstNameTextFieldLocator));
        driver.setText(firstNameTextField, firstName);

        WebElement saveButton = driver.findElement(saveButtonLocator);
        driver.click(saveButton);

        driver.wait(ExpectedConditions.visibilityOfElementLocated(saveProfileConfirmationBannerLocator));
        driver.wait(ExpectedConditions.invisibilityOfElementLocated(saveProfileConfirmationBannerLocator));

        return PageManager.getInstance().instantiateCurrentPage(MyDashboardTab.class);
    }
}
