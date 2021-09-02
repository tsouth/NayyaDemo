package com.mindr.tests.employee;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.pages.eventpage.EventRegistrationCancellationModal;
import com.mindr.pages.eventpage.EventRegistrationConfirmationModal;
import com.mindr.pages.homepage.EventsTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.tests.listeners.TakeScreenshotOnFailureListener;
import com.mindr.utilities.email.EmailUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class EmployeeEventTests {
    private final EmailUtility emailUtility = new EmailUtility();

    @Parameters({ "environment" })
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRegisterThenLeaveEvent() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnEmployee();
        EventsTab eventsTab = myDashboardTab.events();
        EventPage eventPage = eventsTab.clickEventTile();
        EventRegistrationConfirmationModal eventRegistrationConfirmationModal = eventPage.registerForEvent();
        eventRegistrationConfirmationModal.closeRegistrationModal();

        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }

            email = emailUtility.getEmail("Inbox", "You're attending");
            if (email != null && !email.equals("")) {
                break;
            }
        }

        EventRegistrationCancellationModal eventRegistrationCancellationModal = eventPage.cancelEventRegistration();
        eventRegistrationCancellationModal.confirmLeave();

        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }

            email = emailUtility.getEmail("Inbox", "You're no longer attending");
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
