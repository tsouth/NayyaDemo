package com.mindr.tests.admin;

import com.mindr.pages.calltoactionpage.CallToActionPage;
import com.mindr.pages.communitypages.callstoactionpages.ActiveCallsToActionTab;
import com.mindr.pages.communitypages.callstoactionpages.NewCallToActionPage;
import com.mindr.pages.communitypages.callstoactionpages.PublishCallToActionConfirmationModal;
import com.mindr.pages.communitypages.callstoactionpages.UploadCallToActionPhotoModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Listeners(TakeScreenshotOnFailureListener.class)
public class AdminCallToActionTests implements TestCase {
    private String testImagePath;
    private final ImageUtility imageUtility = new ImageUtility();
    private final EmailUtility emailUtility = new EmailUtility();

    @Override
    @Parameters({ "environment" })
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);

        testImagePath = imageUtility.createImage();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateCallToAction() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        ActiveCallsToActionTab activeCallsToActionTab = upcomingEventsTab.callsToAction();
        NewCallToActionPage newCallToActionPage = activeCallsToActionTab.createNewCallToAction();
        UploadCallToActionPhotoModal uploadCallToActionPhotoModal = newCallToActionPage
                .uploadCallToActionPhoto(testImagePath);
        uploadCallToActionPhotoModal.selectPhoto();
        PublishCallToActionConfirmationModal publishCallToActionConfirmationModal = newCallToActionPage
                .submitCallToActionDetails();
        publishCallToActionConfirmationModal.publishCallToAction();
        activeCallsToActionTab.verifyCallToActionCreated();

        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }

            email = emailUtility.getEmail("Inbox", "Selenium Testing Call to Action");
            if (email != null && !email.equals("")) {
                break;
            }
        }

        Matcher urlIds = Pattern.compile("ls/click([^/]+?(?=\"))").matcher(email);
        String urlId;
        if (urlIds.find()) {
            urlId = urlIds.group(1);
        } else {
            throw new TestException("Register For Call to Action URL Not Found");
        }

        CallToActionPage callToActionPage = PageManager.getInstance().navigateToPage(CallToActionPage.class, urlId);
        callToActionPage.verifyCallToActionEmailCreated();

    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
