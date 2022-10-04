package com.mindr.tests.admin;

import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.communitypages.eventspages.NewEventPage;
import com.mindr.pages.communitypages.eventspages.PublishEventConfirmationModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.communitypages.eventspages.UploadEventPhotoModal;
import com.mindr.pages.communitypages.leadershippage.BatchEmailModal;
import com.mindr.pages.communitypages.leadershippage.LeadershipPage;
import com.mindr.pages.eventpage.EventPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.tests.listeners.TakeScreenshotOnFailureListener;
import com.mindr.utilities.email.EmailUtility;
import com.mindr.utilities.image.ImageUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import com.mindr.utilities.testcase.TestCase;
import org.testng.TestException;
import org.testng.annotations.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Listeners(TakeScreenshotOnFailureListener.class)
public class AdminBatchEmailTests implements TestCase {

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
    public void testBatchEmailLeaders() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.Settings();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        LeadershipPage leadershipPage = upcomingEventsTab.leadership();
        BatchEmailModal batchEmailModal = leadershipPage.createBatchEmail();
        batchEmailModal.sendBatchEmail();

        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }

            email = emailUtility.getEmail("Inbox", "Batch Email Test Subject Line");
            if (email != null && !email.equals("")) {
                break;
            }
        }
    }

    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
