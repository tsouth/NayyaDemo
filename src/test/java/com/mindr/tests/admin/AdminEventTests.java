package com.mindr.tests.admin;

import com.mindr.pages.communitypages.eventspages.NewEventPage;
import com.mindr.pages.communitypages.eventspages.PublishEventConfirmationModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.communitypages.eventspages.UploadEventPhotoModal;
import com.mindr.pages.dashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.eventpage.EventPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.utilities.email.EmailCredentialUtility;
import com.mindr.utilities.email.EmailUtility;
import com.mindr.utilities.image.ImageUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.openqa.selenium.devtools.v84.page.Page;
import org.testng.TestException;
import org.testng.annotations.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminEventTests {
    private String testImagePath;
    private final ImageUtility imageUtility = new ImageUtility();
    private final EmailCredentialUtility emailCredentialUtility = new EmailCredentialUtility();
    private final EmailUtility emailUtility = new EmailUtility();

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);

        testImagePath = imageUtility.createImage();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateEvent() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        NewEventPage newEventPage = upcomingEventsTab.createNewEvent();
        UploadEventPhotoModal uploadEventPhotoModal = newEventPage.uploadEventPhoto(testImagePath);
        uploadEventPhotoModal.selectPhoto();
        PublishEventConfirmationModal publishEventConfirmationModal = newEventPage.submitEventDetails();
        publishEventConfirmationModal.publishEvent();
        upcomingEventsTab.verifyEventCreated();

        String[] testPath = getClass().getName().split("\\.");
        String emailAddress = emailCredentialUtility.getRandomizedEmail(testPath[testPath.length - 1].toLowerCase());
        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {}

            email = emailUtility.getEmail("Inbox", emailAddress, "Invitation: Selenium Testing Event");
            if (email != null && !email.equals("")) {
                break;
            }
        }

        Matcher urlIds = Pattern.compile("ls/click([^/]+?(?=\"))").matcher(email);
        String urlId;
        if (urlIds.find()) {
            urlId = urlIds.group(1);
        } else {
            throw new TestException("Register For Event URL Not Found");
        }

        EventPage eventPage = PageManager.getInstance().navigateToPage(EventPage.class, urlId);
        eventPage.verifyEventEmailCreated();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
