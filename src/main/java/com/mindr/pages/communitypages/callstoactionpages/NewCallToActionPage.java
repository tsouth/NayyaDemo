package com.mindr.pages.communitypages.callstoactionpages;

import com.mindr.utilities.date.MindrDate;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class NewCallToActionPage implements BasePage {
    private final MindrDriver driver;

    private final By publishButtonLocator = By.id("publish");
    private final By callToActionTitleFieldLocator = By.id("call_title");
    private final By datePickerFieldLocator = By.id("call_formatted_date");
    private final By streetAddressFieldLocator = By.xpath("//input[@class='input pac-target-input']");
    private final By timeToCompleteFieldLocator = By.id("call_time_to_complete_num");
    private final By callToActionDescriptionFieldLocator = By.id("call_description");
    private final By locationTypeFieldLocator = By.id("location-type-selectized");
    private final By inPersonEventTypeSelector = By.cssSelector(".option:nth-child(2)");
    private final By addCallToActionImageFileInputLocator = By.xpath("//*[@id=\"call_images_attributes_0_image\"]");

    public NewCallToActionPage(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    MindrDate date = new MindrDate();

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(publishButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public PublishCallToActionConfirmationModal submitCallToActionDetails() {
        WebElement callToActionTitleTextField = driver
                .wait(ExpectedConditions.visibilityOfElementLocated(callToActionTitleFieldLocator));
        MindrDate timestamp = date.dateAndTime();
        driver.setText(callToActionTitleTextField, "Selenium Testing Call to Action: " + timestamp);

        WebElement datePickerField = driver.findElement(datePickerFieldLocator);
        driver.setText(datePickerField, "Mon, Feb 14th, 2050");

        selectInPersonCallToActionType();
        WebElement streetAddressField = driver.findElement(streetAddressFieldLocator);
        driver.setText(streetAddressField, "20 W 34th St, New York, NY, USA");
        driver.findElements(By.cssSelector(".pac-item")).get(0).click();

        WebElement timeToCompleteField = driver.findElement(timeToCompleteFieldLocator);
        driver.setText(timeToCompleteField, "45");

        WebElement callToActionDescriptionField = driver.findElement(callToActionDescriptionFieldLocator);
        driver.setText(callToActionDescriptionField, "Testing");

        WebElement publishButton = driver.wait(ExpectedConditions.elementToBeClickable(publishButtonLocator));
        driver.click(publishButton);

        return PageManager.getInstance().instantiateCurrentPage(PublishCallToActionConfirmationModal.class);
    }

    public UploadCallToActionPhotoModal uploadCallToActionPhoto(String testImagePath) {
        WebElement addCallToActionImageFileInput = driver
                .wait(ExpectedConditions.presenceOfElementLocated(addCallToActionImageFileInputLocator));
        driver.setText(addCallToActionImageFileInput, testImagePath);

        return PageManager.getInstance().instantiateCurrentPage(UploadCallToActionPhotoModal.class);
    }

    public NewCallToActionPage selectInPersonCallToActionType() {
        WebElement locationTypeField = driver.findElement(locationTypeFieldLocator);
        driver.click(locationTypeField);
        WebElement inPersonType = driver.wait(ExpectedConditions.presenceOfElementLocated(inPersonEventTypeSelector));
        driver.click(inPersonType);

        return PageManager.getInstance().instantiateCurrentPage(NewCallToActionPage.class);
    }
}
