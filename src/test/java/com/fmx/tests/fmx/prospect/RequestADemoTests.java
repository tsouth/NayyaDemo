package com.fmx.tests.fmx.prospect;

import com.fmx.pages.homePage.HamburgerMenu;
import com.fmx.pages.homePage.HomePage;
import com.fmx.pages.requestADemoPages.RequestADemoConfirmationPage;
import com.fmx.pages.requestADemoPages.RequestADemoForm;
import com.fmx.utilities.testcase.TakeScreenshotOnFailureListener;
import com.fmx.utilities.managers.PageManager;
import com.fmx.utilities.testcase.RetryAnalyzer;
import com.fmx.utilities.testcase.TestCase;
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
        RequestADemoForm requestADemoForm;
        if (PageManager.getInstance().isSupportedMobileDevice()) {
            HamburgerMenu hamburgerMenu = homePage.openHamburgerMenu();
            requestADemoForm = hamburgerMenu.requestADemo();
        } else {
            requestADemoForm = homePage.requestADemo();
        }
        RequestADemoConfirmationPage requestADemoConfirmationPage = requestADemoForm.submitRequestForm();
        requestADemoConfirmationPage.verifyAppointmentRequest();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
