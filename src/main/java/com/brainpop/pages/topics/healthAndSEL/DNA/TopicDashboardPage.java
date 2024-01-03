package com.brainpop.pages.topics.healthAndSEL.DNA;

import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import com.brainpop.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.List;
import java.util.Random;


public class TopicDashboardPage implements BasePage, PageNavigation {
    private final BrainPopDriver driver;
    private final String URL = "/health/geneticsgrowthanddevelopment/dna/";

    private final By watchMovieButtonLocator = By.id("play_movie");
    private final By DNATitleLocator = By.id("rich_breadcrumb_item_topicname");
    private final By featuresLocator = By.cssSelector("a[id^='feature']");
    private final By relatedReadingFeatureLocator = By.id("feature_related_reading");


    public TopicDashboardPage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {driver.wait(ExpectedConditions.visibilityOfElementLocated(DNATitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getBrainPopUrl() + URL);
    }

    public MoviePage watchMovie() {
        WebElement watchMovieButton = driver.wait(ExpectedConditions.elementToBeClickable(watchMovieButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", watchMovieButton);
        driver.click(watchMovieButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public RelatedReadingPage selectRelatedReadingFeature(){
        WebElement relatedReadingFeature = driver.wait(ExpectedConditions.elementToBeClickable(relatedReadingFeatureLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", relatedReadingFeature);
        driver.click(relatedReadingFeature);

        return  PageManager.getInstance().instantiateCurrentPage(RelatedReadingPage.class);
    }

    public void selectRandomFeature() {
        List<WebElement> featureList = driver.wait(ExpectedConditions.presenceOfAllElementsLocatedBy(featuresLocator));
        WebElement feature = driver.wait(ExpectedConditions.elementToBeClickable(
                featureList.get(new Random().nextInt(featureList.size()))));
        driver.executeScript("arguments[0].scrollIntoView(true);", feature);
        driver.click(feature);
    }
}
