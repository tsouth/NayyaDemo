package com.mindr.tests.admin;

import com.mindr.pages.communitypages.eventspages.NewEventPage;
import com.mindr.pages.communitypages.eventspages.PublishEventConfirmationModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.communitypages.eventspages.UploadEventPhotoModal;
import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.eventpage.EventPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.tests.listeners.TakeScreenshotOnFailureListener;
import com.mindr.utilities.email.EmailUtility;
import com.mindr.utilities.image.ImageUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import com.mindr.utilities.testcase.TestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Listeners(TakeScreenshotOnFailureListener.class)
public class AdminEventTests implements TestCase {
    private String testImagePath;
    private final ImageUtility imageUtility = new ImageUtility();
    private final EmailUtility emailUtility = new EmailUtility();

    @Override
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
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.Settings();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        NewEventPage newEventPage = upcomingEventsTab.createNewEvent();
        UploadEventPhotoModal uploadEventPhotoModal = newEventPage.uploadEventPhoto(testImagePath);
        uploadEventPhotoModal.selectPhoto();
        PublishEventConfirmationModal publishEventConfirmationModal = newEventPage.submitEventDetails();
        publishEventConfirmationModal.publishEvent();
        upcomingEventsTab.verifyEventCreated();

        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {

            }

            email = emailUtility.getEmail("Inbox", "Invitation: Selenium Testing Event:");
            if (email != null && !email.equals("")) {
                break;
            }
        }

        if (email == null || email.equals("")) {
            Assert.fail("Email not found");
        }

        Matcher urlIds = Pattern.compile("ls/click([^/]+?(?=\"))").matcher(email);
        String urlId;
        List<String> urls = urlIds.results().map(m -> m.group(1)).collect(Collectors.toList());

        if (urls.size() >= 2) {
            urlId = urls.get(1);
        } else {
            throw new TestException("Register For Event URL Not Found");
        }

        EventPage eventPage = PageManager.getInstance().navigateToPage(EventPage.class, urlId);
        eventPage.verifyEventEmailCreated();
    }

    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
