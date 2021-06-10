package com.mindr.pages.communitypages.eventspage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class NewEventPage implements BasePage {
    private final MindrDriver driver;

    private final String testImagePath = System.getProperty("user.dir") + "/src/main/resources/images/testImage.jpg";

    private final By publishButtonLocator = By.id("publish");
    private final By eventTitleFieldLocator = By.id("event_title");
    private final By datePickerFieldLocator = By.id("event_formatted_date");
    private final By startTimeFieldLocator = By.id("event_formatted_time");
    private final By endTimeFieldLocator = By.id("event_formatted_time_to");
    private final By timeZoneFieldLocator = By.xpath("//div[@class='selectize-dropdown-content']");
    private final By streetAddressFieldLocator = By.xpath("//input[contains(., 'Please enter the street address')]");
    private final By eventDescriptionFieldLocator = By.id("event_description");
    private final By addEventImageLinkLocator = By.xpath("//div[@class='link image'][contains(., '+ Add Image')]");

    public NewEventPage(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

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
        WebElement eventTitleTextField = driver.wait(ExpectedConditions.visibilityOfElementLocated(eventTitleFieldLocator));
        String timestamp = Long.toString(System.currentTimeMillis());
        driver.setText(eventTitleTextField, "Selenium Testing: " + timestamp);

        WebElement datePickerField = driver.wait(ExpectedConditions.visibilityOfElementLocated(datePickerFieldLocator));
        driver.setText(datePickerField,"Mon, Feb 14th, 2050" );

        WebElement startTimeField = driver.wait(ExpectedConditions.visibilityOfElementLocated(startTimeFieldLocator));
        driver.setText(startTimeField, "11:00am");

        WebElement endTimeField = driver.wait(ExpectedConditions.visibilityOfElementLocated(endTimeFieldLocator));
        driver.setText(endTimeField, "4:00pm");

//        WebElement timeZoneField = driver.wait(ExpectedConditions.visibilityOfElementLocated(timeZoneFieldLocator));
//        driver.setText(timeZoneField, "EDT");

        WebElement streetAddressField = driver.wait(ExpectedConditions.visibilityOfElementLocated(streetAddressFieldLocator));
        driver.setText(streetAddressField, "20 W 34th St, New York, NY, USA");

        WebElement eventDescriptionField = driver.wait(ExpectedConditions.visibilityOfElementLocated(eventDescriptionFieldLocator));
        driver.setText(eventDescriptionField, "Testing");

        WebElement publishButton = driver.wait(ExpectedConditions.elementToBeClickable(publishButtonLocator));
        driver.click(publishButton);

        return PageManager.getInstance().instantiateCurrentPage(PublishEventConfirmationModal.class);
    }

    public UploadedEventPhotoModal uploadEventPhoto() {
        WebElement addEventImageLink = driver.findElement(addEventImageLinkLocator);
        driver.setText(addEventImageLink, testImagePath);

        return PageManager.getInstance().instantiateCurrentPage(UploadedEventPhotoModal.class);
    }
}
