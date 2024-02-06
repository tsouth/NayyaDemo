package com.steno.pages.talkToSalesPages;

import com.steno.utilities.managers.PageManager;
import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.PageNavigation;
import com.steno.utilities.page.StenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.TestException;
import java.util.List;
import java.util.Random;

public class TalkToSalesForm implements BasePage, PageNavigation {
    private final StenoDriver driver;

    private final By firstNameFieldLocator = By.xpath("//input[@name='firstname']");
    private final By lastNameFieldLocator = By.xpath("//input[@name='lastname']");
    private final By jobTitleFieldLocator = By.xpath("//select[@name='job_title']");
    private final By businessEmailAddressFieldLocator = By.xpath("//input[@name='email']");
    private final By phoneNumberFieldLocator = By.xpath("//input[@name='phone']");
    private final By firmNameFieldLocator = By.xpath("//input[@name='company']");
    private final By locationFieldLocator = By.xpath("//input[@name='address']");
    private final By requestDetailsFieldLocator = By.xpath("//textarea[@name='message']");
    private final By smsConsentCheckbotLocator = By.xpath("//input[@name='sms_consent']");
    private final By submitSalesFormButtonLocator = By.xpath("//input[@type='submit'][@value=Submit]");

    public TalkToSalesForm(WebDriver driver) {
        this.driver = new StenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(firstNameFieldLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getstenoUrl());
    }

    public SalesFormSubmissionConfirmationPage submitSalesForm() throws InterruptedException {
        String timestamp = Long.toString(System.currentTimeMillis());
        String phoneNumber = "777777777";

        WebElement firstNameField = driver.wait(ExpectedConditions.visibilityOfElementLocated(firstNameFieldLocator));
        driver.setText(firstNameField, "Steno QA");

        WebElement lastNameField = driver.findElement(lastNameFieldLocator);
        driver.setText(lastNameField, "Testing Request A Discussion");

        selectJobTitle();

        WebElement businessEmailAddressField = driver.findElement(businessEmailAddressFieldLocator);
        driver.setText(businessEmailAddressField, "thomas+stenoqa_" + timestamp + "@tsouth.me");

        WebElement phoneNumberField = driver.findElement(phoneNumberFieldLocator);
        driver.setText(phoneNumberField, phoneNumber);

        WebElement firmNameField = driver.findElement(firmNameFieldLocator);
        driver.setText(firmNameField, "Steno QA Organization");

        WebElement locationField = driver.findElement(locationFieldLocator);
        driver.setText(locationField, "New York, NY");

        WebElement requestDetailsField = driver.findElement(requestDetailsFieldLocator);
        driver.setText(requestDetailsField, "This is a test form submission. Please disregard.");

        WebElement submitSalesFormButton = driver.wait(ExpectedConditions.elementToBeClickable(submitSalesFormButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", submitSalesFormButton);
//        driver.click(submitSalesFormButton);

        return PageManager.getInstance().instantiateCurrentPage(SalesFormSubmissionConfirmationPage.class);
    }

    public TalkToSalesForm selectJobTitle() {
        WebElement organizationTypeList = driver.wait(ExpectedConditions.presenceOfElementLocated(jobTitleFieldLocator));
        Select select = new Select(organizationTypeList);
        List<WebElement> organizationTypes = select.getOptions();
        WebElement type = driver.wait(ExpectedConditions.elementToBeClickable(
                organizationTypes.get(new Random().nextInt(organizationTypes.size()))));
        driver.click(type);

        return PageManager.getInstance().instantiateCurrentPage(TalkToSalesForm.class);
    }
}
