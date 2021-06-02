package com.mindr.tests.employee;

import com.mindr.pages.dashboardpages.communitysubscriptionspage.CommunitySubscriptionsPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class EmployeeProfileTests {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testModifyProfile() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsEmployee();
        CommunitySubscriptionsPage communitySubscriptionsPage = myDashboardTab.viewMyProfile();
        EditProfilePage editProfilePage = communitySubscriptionsPage.editProfile();
        editProfilePage.setProfileFirstName("QA Employee");
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}

