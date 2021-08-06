package com.mindr.pages.communitypages.eventspages;

import com.mindr.utilities.date.MindrDate;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class NewEventPage implements BasePage {
    private final MindrDriver driver;

    private final By publishButtonLocator = By.id("publish");
    private final By eventTitleFieldLocator = By.id("event_title");
    private final By datePickerFieldLocator = By.id("event_formatted_date");
    private final By startTimeFieldLocator = By.id("event_formatted_time");
    private final By endTimeFieldLocator = By.id("event_formatted_time_to");
    private final By streetAddressFieldLocator = By.xpath("//*[@id=\"new_event\"]/section[1]/div[5]/div[2]/div/div/input");
    private final By eventDescriptionFieldLocator = By.id("event_description");
    private final By addEventImageFileInputLocator = By.xpath("//*[@id=\"event_images_attributes_0_image\"]");

    public NewEventPage(WebDriver driver) {
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

    public PublishEventConfirmationModal submitEventDetails() {
        MindrDate timestamp = date.dateAndTime();

        WebElement eventTitleTextField = driver.wait(ExpectedConditions.visibilityOfElementLocated(eventTitleFieldLocator));
        driver.setText(eventTitleTextField, "Selenium Testing Event: " + timestamp);

        WebElement datePickerField = driver.findElement(datePickerFieldLocator);
        driver.setText(datePickerField,"Mon, Feb 14th, 2050" );

        WebElement startTimeField = driver.findElement(startTimeFieldLocator);
        driver.setText(startTimeField, "11:00am");

        WebElement endTimeField = driver.findElement(endTimeFieldLocator);
        driver.setText(endTimeField, "4:00pm");

        WebElement streetAddressField = driver.findElement(streetAddressFieldLocator);
        driver.setText(streetAddressField, "20 W 34th St, New York, NY, USA");
        driver.findElements(By.cssSelector(".pac-item")).get(0).click();

        WebElement eventDescriptionField = driver.findElement(eventDescriptionFieldLocator);
        driver.setText(eventDescriptionField, "Testing");

        WebElement publishButton = driver.wait(ExpectedConditions.elementToBeClickable(publishButtonLocator));
        driver.click(publishButton);

        return PageManager.getInstance().instantiateCurrentPage(PublishEventConfirmationModal.class);
    }

    public UploadEventPhotoModal uploadEventPhoto(String testImagePath) {
        WebElement addEventImageFileInput = driver.wait(ExpectedConditions.presenceOfElementLocated(addEventImageFileInputLocator));
        driver.setText(addEventImageFileInput, testImagePath);

        return PageManager.getInstance().instantiateCurrentPage(UploadEventPhotoModal.class);
    }
}
