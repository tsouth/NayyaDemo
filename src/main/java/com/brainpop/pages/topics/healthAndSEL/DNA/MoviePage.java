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


public class MoviePage implements BasePage, PageNavigation {
    private final BrainPopDriver driver;
    private final String URL = "/health/geneticsgrowthanddevelopment/dna/movie";

    private final By playMovieButtonLocator = By.id("play");
    private final By pauseMovieButtonLocator = By.id("pause");
    private final By progressBarLocator = By.id("progress-bar");
    private final By closedCaptionButtonLocator = By.id("caption");
    private final By captionTextLocator = By.xpath("//div[@id='movie_container']/div[2]/div/span");


    public MoviePage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {driver.wait(ExpectedConditions.visibilityOfElementLocated(playMovieButtonLocator));
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

    public MoviePage playMovie() {
        WebElement playMovieButton = driver.wait(ExpectedConditions.elementToBeClickable(playMovieButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", playMovieButton);
        driver.click(playMovieButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage pauseMovie() {
        WebElement pauseMovieButton = driver.wait(ExpectedConditions.elementToBeClickable(pauseMovieButtonLocator));
        driver.click(pauseMovieButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public String getMovieProgress() {
        WebElement progressBar = driver.findElement(progressBarLocator);

        return (progressBar.getAttribute("value"));
    }
    public MoviePage enableClosedCaptioning() {
        WebElement closedCaptionButton = driver.wait(ExpectedConditions.visibilityOfElementLocated(closedCaptionButtonLocator));
        driver.click(closedCaptionButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage disableClosedCaptioning() {
        WebElement closedCaptionButton = driver.findElement(closedCaptionButtonLocator);
        driver.click(closedCaptionButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public String getCaptionText() {
        WebElement captionText = driver.findElement(captionTextLocator);

        return captionText.getText();
    }
}
