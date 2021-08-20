package com.mindr.pages.admindashboardpages.communitymanagementpage;

import com.mindr.pages.communitypages.callstoactionpages.PublishCallToActionConfirmationModal;
import com.mindr.pages.communitypages.callstoactionpages.UploadCallToActionPhotoModal;
import com.mindr.utilities.managers.DriverManager;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class NewCommunityPage implements BasePage {
    private final MindrDriver driver;

    private final By createCommunityButtonLocator = By.xpath("//input[@value='Create Community']");
    private final By communityTitleFieldLocator = By.id("community_name");
    private final By communitySubtitleFieldLocator = By.id("community_subtitle");
    private final By communityMissionStatementFieldLocator = By.id("community_mission_statement");
    private final By communityLeaderFieldLocator = By.name("community[leaders_attributes][0][user_id]");
    private final By addCommunityLogoImageFileInputLocator = By.id("community_logo");
    private final By addCommunityThumbnailImageFileInputLocator = By.id("community_thumbnail");
    private final By addCommunityBannerImageFileInputLocator = By.id("community_banner");
    private final By addCommunityFeaturedImageOneFileInputLocator = By.xpath("//*[@id='community_images_attributes_0_image']");
    private final By addCommunityFeaturedImageTwoFileInputLocator = By.xpath("//*[@id='community_images_attributes_1_image']");
    private final By selectPhotoButtonLocator = By.xpath("/html/body/div[7]/div/div[3]/button[1]");

    public NewCommunityPage (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(createCommunityButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public ActiveCommunitiesTab submitCommunityDetails() {
        WebElement communityName = driver.wait(ExpectedConditions.visibilityOfElementLocated(communityTitleFieldLocator));
        String timestamp = Long.toString(System.currentTimeMillis());
        driver.setText(communityName, "Selenium Testing Community: " + timestamp);

        WebElement communitySubtitle = driver.findElement(communitySubtitleFieldLocator);
        driver.setText(communitySubtitle, "Test Community Subtitle");

        WebElement communityMissionStatement = driver.findElement(communityMissionStatementFieldLocator);
        driver.setText(communityMissionStatement, "Test Community Mission Statement");

        WebElement communityLeadershipTeam = driver.findElement(communityLeaderFieldLocator);
        driver.setText(communityLeadershipTeam, "mindrqa+orgadmin@gmail.com");
        driver.findElements(By.cssSelector(".pac-item")).get(0).click();

        WebElement createCommunityButton = driver.wait(ExpectedConditions.elementToBeClickable(createCommunityButtonLocator));
        driver.click(createCommunityButton);

        return PageManager.getInstance().instantiateCurrentPage(ActiveCommunitiesTab.class);
    }

    public NewCommunityPage uploadCommunityPhotos(String testImagePath) {
        WebElement selectPhotoButton = driver.findElement(selectPhotoButtonLocator);

        WebElement addCommunityLogoImageFileInput = driver.wait(ExpectedConditions.presenceOfElementLocated(addCommunityLogoImageFileInputLocator));
        driver.setText(addCommunityLogoImageFileInput, testImagePath);
        driver.click(selectPhotoButton);

        WebElement addCommunityThumbnailImageInput = driver.wait(ExpectedConditions.presenceOfElementLocated(addCommunityThumbnailImageFileInputLocator));
        driver.setText(addCommunityThumbnailImageInput, testImagePath);
        driver.click(selectPhotoButton);

        WebElement addCommunityBannerImageInput = driver.wait(ExpectedConditions.presenceOfElementLocated(addCommunityBannerImageFileInputLocator));
        driver.setText(addCommunityBannerImageInput, testImagePath);
        driver.click(selectPhotoButton);

        WebElement addFeaturedImageOneInput = driver.wait(ExpectedConditions.presenceOfElementLocated(addCommunityFeaturedImageOneFileInputLocator));
        driver.setText(addFeaturedImageOneInput, testImagePath);
        driver.click(selectPhotoButton);

        WebElement addFeaturedImageTwoInput = driver.wait(ExpectedConditions.presenceOfElementLocated(addCommunityFeaturedImageTwoFileInputLocator));
        driver.setText(addFeaturedImageTwoInput, testImagePath);
        driver.click(selectPhotoButton);

        return PageManager.getInstance().instantiateCurrentPage(NewCommunityPage.class);
    }
}
