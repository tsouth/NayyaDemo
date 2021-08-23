package com.mindr.pages.admindashboardpages.communitymanagementpage;

import com.mindr.pages.communitypages.callstoactionpages.ActiveCallsToActionTab;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class ActiveCommunitiesTab implements BasePage {
    private final MindrDriver driver;

    private final By communityLocator = By.cssSelector("a[href*='/dashboard/communities/1/events']");
    private final By editProfileLinkLocator =  By.cssSelector("a[href*='/dashboard/profile/edit']");
    private final By newCommunityButtonLocator = By.cssSelector("a[href*='/dashboard/communities/new']");
    private final By successfulCommunityCreationBannerLocator = By.xpath("//div[@class='message']");


    public ActiveCommunitiesTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(communityLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public UpcomingEventsTab selectCommunity() {
        WebElement community = driver.wait(ExpectedConditions.visibilityOfElementLocated(communityLocator));
        driver.click(community);

        return PageManager.getInstance().instantiateCurrentPage(UpcomingEventsTab.class);
    }

    public EditProfilePage editProfile() {
        WebElement editProfileLink = driver.wait(ExpectedConditions.elementToBeClickable(
                editProfileLinkLocator));
        driver.click(editProfileLink);

        return PageManager.getInstance().instantiateCurrentPage(EditProfilePage.class);
    }

    public NewCommunityPage createNewCommunity() {
        WebElement newCommunityButton = driver.wait(ExpectedConditions.elementToBeClickable(newCommunityButtonLocator));
        driver.click(newCommunityButton );

        return PageManager.getInstance().instantiateCurrentPage(NewCommunityPage.class);
    }

    public ActiveCommunitiesTab verifyCommunityCreated() {
        driver.wait(ExpectedConditions.presenceOfElementLocated(successfulCommunityCreationBannerLocator));

        return PageManager.getInstance().instantiateCurrentPage(ActiveCommunitiesTab.class);
    }
}