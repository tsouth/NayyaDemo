package com.mindr.tests.employee;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.pages.eventpage.RegistrationCancellationModal;
import com.mindr.pages.homepage.EventsTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class EmployeeEventTests {
    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRegisterForEvent() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsEmployee();
        EventsTab eventsTab = myDashboardTab.events();
        EventPage eventPage = eventsTab.clickEventTile();
        eventPage.registerForEvent();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCancelRegistrationForEvent() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsEmployee();
        EventsTab eventsTab = myDashboardTab.events();
        EventPage eventPage = eventsTab.clickEventTile();
        RegistrationCancellationModal registrationCancellationModal = eventPage.cancelEventRegistration();
        registrationCancellationModal.confirmLeave();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
