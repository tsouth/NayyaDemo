package com.mindr.tests.employee;

import com.mindr.pages.calltoactionpage.CallToActionPage;
import com.mindr.pages.calltoactionpage.CallToActionRegistrationCancellationModal;
import com.mindr.pages.calltoactionpage.CallToActionRegistrationConfirmationModal;

import com.mindr.pages.homepage.CallsToActionTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class EmployeeCallToActionTests {
    @Parameters({"environment"})
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
        CallToActionRegistrationConfirmationModal callToActionRegistrationConfirmationModal =
                callToActionPage.registerForCallToAction();
        callToActionRegistrationConfirmationModal.closeRegistrationModal();
        CallToActionRegistrationCancellationModal callToActionRegistrationCancellationModal =
                callToActionPage.cancelCallToActionRegistration();
        callToActionRegistrationCancellationModal.confirmLeave();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
