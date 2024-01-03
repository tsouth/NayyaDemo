package com.brainpop.pages.topics.healthAndSEL.DNA;

import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import com.brainpop.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;
import java.util.Optional;

public class MoviePage implements BasePage, PageNavigation {
    private final BrainPopDriver driver;

    private final String URL = "/health/geneticsgrowthanddevelopment/dna/movie";

    private final By pageTitleLocator = By.xpath("//span[@class='item_text']");
    private final By playMovieButtonLocator = By.id("play");
    private final By pauseMovieButtonLocator = By.id("pause");
    private final By videoSliderLocator = By.xpath("/html/body/div/div/div/div/div/div/div/div/div/main/div[2]/div/div/div/div/div/div[1]/div/div/div/div[2]/div[1]/div/div/input");
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
        WebElement pageTitle = driver.findElement(pageTitleLocator);
        WebElement playMovieButton = driver.wait(ExpectedConditions.elementToBeClickable(playMovieButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", playMovieButton);
        driver.click(playMovieButton);
        driver.executeScript("arguments[0].scrollIntoView(true);", pageTitle);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage pauseMovie() {
        WebElement pageTitle = driver.findElement(pageTitleLocator);
        WebElement pauseMovieButton = driver.wait(ExpectedConditions.visibilityOfElementLocated(pauseMovieButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", pauseMovieButton);
        driver.click(pauseMovieButton);
        driver.executeScript("arguments[0].scrollIntoView(true);", pageTitle);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage setMovieToFinished() throws InterruptedException {
        WebElement pageTitle = driver.findElement(pageTitleLocator);
        WebElement videoSlider = driver.findElement(videoSliderLocator);
        videoSlider.sendKeys("9");
        for (int i = 0; i < 10; i++) {
            videoSlider.sendKeys(Keys.ARROW_RIGHT);
            Thread.sleep(200);
        }
        driver.executeScript("arguments[0].scrollIntoView(true);", pageTitle);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage takeScreenshot() {
        driver.takeScreenshot(Optional.of("movieScreenshot"));

        return  PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage enableClosedCaptioning() {
        WebElement closedCaptionButton = driver.wait(ExpectedConditions.visibilityOfElementLocated(closedCaptionButtonLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", closedCaptionButton);
        driver.click(closedCaptionButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public MoviePage disableClosedCaptioning() {
        WebElement closedCaptionButton = driver.findElement(closedCaptionButtonLocator);
        driver.executeScript("arguments[0].scrollIntoView(true);", closedCaptionButton);
        driver.click(closedCaptionButton);

        return PageManager.getInstance().instantiateCurrentPage(MoviePage.class);
    }

    public String getCaptionText() {
        WebElement captionText = driver.findElement(captionTextLocator);

        return captionText.getText();
    }
}
