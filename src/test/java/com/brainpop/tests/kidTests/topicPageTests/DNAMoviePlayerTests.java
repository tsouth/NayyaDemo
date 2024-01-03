package com.brainpop.tests.kidTests.topicPageTests;

import com.brainpop.pages.topics.healthAndSEL.DNA.MoviePage;
import com.brainpop.pages.topics.healthAndSEL.DNA.TopicDashboardPage;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.testcase.RetryAnalyzer;
import com.brainpop.utilities.testcase.TestCase;
import org.testng.Assert;
import org.testng.annotations.*;

public class DNAMoviePlayerTests implements TestCase {
    @Override
    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testMoviePlaysAndEnds() throws InterruptedException {
        TopicDashboardPage topicDashboardPage = PageManager.getInstance().navigateToPage(TopicDashboardPage.class);
        MoviePage moviePage = topicDashboardPage.watchMovie();
        moviePage.playMovie();
        moviePage.pauseMovie();
        Object beginningMovieStill = moviePage.takeScreenshot();

        moviePage.setMovieToFinished();
        Object endingMovieStill = moviePage.takeScreenshot();

        //The remainder of this test would assert that the user was able to play the video and also see the ending.
        //My thought process was to capture two images(a beginning and end) then visually compare the two
        //The function would be to buffer the images to the same size, capture RGB at coordinates x and y, then assert
        //This is something I've never done before and would want to confirm it was the right approach before investment
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testClosedCaptionActivation() throws InterruptedException {
        TopicDashboardPage topicDashboardPage = PageManager.getInstance().navigateToPage(TopicDashboardPage.class);
        MoviePage moviePage = topicDashboardPage.watchMovie();
        moviePage.playMovie();
        Thread.sleep(3000); //Implemented to slow down the webdriver so that its interactions will not be flakey
        moviePage.pauseMovie();

        moviePage.enableClosedCaptioning();
        String captions = moviePage.getCaptionText();
        Assert.assertNotNull(captions);
        moviePage.disableClosedCaptioning();
    }

    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
