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

        Assert.assertNotEquals(beginningMovieStill, endingMovieStill);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testClosedCaptionActivation() throws InterruptedException {
        TopicDashboardPage topicDashboardPage = PageManager.getInstance().navigateToPage(TopicDashboardPage.class);
        MoviePage moviePage = topicDashboardPage.watchMovie();
        moviePage.playMovie();
        Thread.sleep(3000); //So Sorry to implement an explicit wait here. Pair up on a better solution?
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
