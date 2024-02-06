package com.steno.tests.steno.prospect;

import com.steno.pages.homePage.HamburgerMenu;
import com.steno.pages.homePage.HomePage;
import com.steno.pages.talkToSalesPages.SalesFormSubmissionConfirmationPage;
import com.steno.pages.talkToSalesPages.TalkToSalesForm;
import com.steno.utilities.testcase.TakeScreenshotOnFailureListener;
import com.steno.utilities.managers.PageManager;
import com.steno.utilities.testcase.RetryAnalyzer;
import com.steno.utilities.testcase.TestCase;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class RequestADemoTests implements TestCase {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRequestADemo() throws InterruptedException {
        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        TalkToSalesForm requestADemoForm;
        if (PageManager.getInstance().isSupportedMobileDevice()) {
            HamburgerMenu hamburgerMenu = homePage.openHamburgerMenu();
            requestADemoForm = hamburgerMenu.requestADemo();
        } else {
            requestADemoForm = homePage.requestADemo();
        }
        SalesFormSubmissionConfirmationPage requestADemoConfirmationPage = requestADemoForm.submitRequestForm();
        requestADemoConfirmationPage.verifyAppointmentRequest();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
