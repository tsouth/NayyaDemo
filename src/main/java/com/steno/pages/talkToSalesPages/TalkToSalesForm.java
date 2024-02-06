package com.steno.pages.requestADemoPages;

import com.steno.utilities.managers.PageManager;
import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.PageNavigation;
import com.steno.utilities.page.stenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.TestException;
import java.util.List;
import java.util.Random;

public class RequestADemoForm implements BasePage, PageNavigation {
    private final stenoDriver driver;

    private final By scheduleDemoButtonLocator = By.id("gform_submit_button_1");
    private final By firstNameFieldLocatorAgain = By.xpath("//div[@name=][]");
    private final By firstNameFieldLocator = By.id("input_1_1"); //input_1_1_1
    private final By lastNameFieldLocator = By.id("input_1_3");
    private final By emailAddressFieldLocator = By.id("input_1_4");
    private final By phoneNumberFieldLocator = By.id("input_1_5");
    private final By organizationNameFieldLocator = By.id("input_1_6");
    private final By organizationTypeListLocator = By.id("input_1_7");
    private final By studentEnrollmentFieldLocator = By.id("input_1_20");
    private final By howDidYouHearListLocator = By.id("input_1_23");
    private final By usersMangingOrClosingFieldLocator = By.id("input_1_24");

    public RequestADemoForm(WebDriver driver) {
        this.driver = new stenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(scheduleDemoButtonLocator));
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

    public RequestADemoConfirmationPage submitRequestForm() throws InterruptedException {
        String timestamp = Long.toString(System.currentTimeMillis());
        String phoneNumber = "777777777";

        WebElement firstNameField = driver.wait(ExpectedConditions.visibilityOfElementLocated(firstNameFieldLocator));
        driver.setText(firstNameField, "stenoQA");

        WebElement lastNameField = driver.findElement(lastNameFieldLocator);
        driver.setText(lastNameField, "Testing Request A Demo");

        WebElement emailAddressField = driver.findElement(emailAddressFieldLocator);
        driver.setText(emailAddressField, "thomas+stenoqa_" + timestamp + "@tsouth.me");

        WebElement phoneNumberField = driver.findElement(phoneNumberFieldLocator);
        driver.setText(phoneNumberField, phoneNumber);
        WebElement organizationNameField = driver.findElement(organizationNameFieldLocator);
        driver.setText(organizationNameField, "steno QA Organization");

        selectOrganizationType();
        selectHowDidYouHearOption();

        WebElement scheduleDemoButton = driver.wait(ExpectedConditions.elementToBeClickable(scheduleDemoButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", scheduleDemoButton);
        driver.executeScript("window.scrollBy(0,-250)");
//        driver.click(scheduleDemoButton);

        return PageManager.getInstance().instantiateCurrentPage(RequestADemoConfirmationPage.class);
    }

    public RequestADemoForm selectOrganizationType() {
        WebElement organizationTypeList = driver.wait(ExpectedConditions.presenceOfElementLocated(organizationTypeListLocator));
        Select select = new Select(organizationTypeList);
        List<WebElement> organizationTypes = select.getOptions();
        WebElement type = driver.wait(ExpectedConditions.elementToBeClickable(
                organizationTypes.get(new Random().nextInt(organizationTypes.size()))));
        driver.click(type);

        if (driver.findElement(studentEnrollmentFieldLocator).isDisplayed()
        ) {
            WebElement studentEnrollmentField = driver.wait(ExpectedConditions.visibilityOfElementLocated(studentEnrollmentFieldLocator));
            driver.setText(studentEnrollmentField, "30");
        } else {
            selectUsersManagingOrClosingOption();
        }

        return PageManager.getInstance().instantiateCurrentPage(RequestADemoForm.class);
    }

    public RequestADemoForm selectUsersManagingOrClosingOption() {
        WebElement usersManagingOrClosingList = driver.wait(ExpectedConditions.presenceOfElementLocated(usersMangingOrClosingFieldLocator));
        Select select = new Select(usersManagingOrClosingList);
        List<WebElement> userOptions = select.getOptions();
        WebElement option = driver.wait(ExpectedConditions.elementToBeClickable(
                userOptions.get(new Random().nextInt(userOptions.size()))));
        driver.click(option);

        return PageManager.getInstance().instantiateCurrentPage(RequestADemoForm.class);
    }

    public RequestADemoForm selectHowDidYouHearOption() {
        WebElement howDidYouHearOptionsList = driver.findElement(howDidYouHearListLocator);
        Select select = new Select(howDidYouHearOptionsList);
        List<WebElement> hearingOptions = select.getOptions();
        WebElement option = driver.wait(ExpectedConditions.elementToBeClickable(
                hearingOptions.get(new Random().nextInt(hearingOptions.size()))));
        driver.click(option);

        return PageManager.getInstance().instantiateCurrentPage(RequestADemoForm.class);
    }
}
