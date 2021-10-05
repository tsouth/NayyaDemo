package com.mindr.tests.admin;

import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.admindashboardpages.communitymanagementpage.NewCommunityPage;
import com.mindr.pages.admindashboardpages.communitymanagementpage.UploadPhotoModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
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
public class AdminCommunityTests implements TestCase {
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
    public void testCreateCommunity() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        NewCommunityPage newCommunityPage = activeCommunitiesTab.createNewCommunity();

        UploadPhotoModal uploadPhotoModal = newCommunityPage.uploadCommunityLogo(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadCommunityThumbnail(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadCommunityBanner(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadFeaturedImageOne(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadFeaturedImageTwo(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.submitCommunityDetails();
        activeCommunitiesTab.verifyCommunityCreated();

        String email = "";
        for (int i = 0; i < emailUtility.getRetryLimit(); i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {

            }

            email = emailUtility.getEmail("Inbox", "You are now a leader");
            if (email != null && !email.equals("")) {
                break;
            }
        }

        Matcher urlIds = Pattern.compile("ls/click([^/]+?(?=\"))").matcher(email);
        String urlId;
        if (urlIds.find()) {
            urlId = urlIds.group(1);
        } else {
            throw new TestException("Manage community URL Not Found");
        }

        UpcomingEventsTab upcomingEventsTab = PageManager.getInstance().navigateToPage(UpcomingEventsTab.class, urlId);
        upcomingEventsTab.verifyCommunityEmailCreated();
    }

    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
