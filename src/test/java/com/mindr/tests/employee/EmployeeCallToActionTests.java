package com.mindr.tests.employee;

import com.mindr.pages.calltoactionpage.CallToActionPage;
import com.mindr.pages.calltoactionpage.CallToActionRegistrationCancellationModal;
import com.mindr.pages.calltoactionpage.CallToActionRegistrationConfirmationModal;

import com.mindr.pages.homepage.CallsToActionTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.tests.listeners.TakeScreenshotOnFailureListener;
import com.mindr.utilities.email.EmailUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class EmployeeCallToActionTests {
    private final EmailUtility emailUtility = new EmailUtility();

    @Parameters({ "environment" })
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRegisterThenLeaveCallToAction() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnEmployee();
        CallsToActionTab callsToActionTab = myDashboardTab.callsToAction();
        CallToActionPage callToActionPage = callsToActionTab.clickCallToActionTile();
        CallToActionRegistrationConfirmationModal callToActionRegistrationConfirmationModal = callToActionPage
                .registerForCallToAction();
        callToActionRegistrationConfirmationModal.closeRegistrationModal();

        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }

            email = emailUtility.getEmail("Inbox", "You're participating");
            if (email != null && !email.equals("")) {
                break;
            }
        }

        CallToActionRegistrationCancellationModal callToActionRegistrationCancellationModal = callToActionPage
                .cancelCallToActionRegistration();
        callToActionRegistrationCancellationModal.confirmLeave();

        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }

            email = emailUtility.getEmail("Inbox", "You're no longer participating");
            if (email != null && !email.equals("")) {
                break;
            }
        }
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
