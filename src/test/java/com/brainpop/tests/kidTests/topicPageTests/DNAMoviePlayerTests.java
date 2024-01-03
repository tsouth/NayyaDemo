package com.brainpop.tests.kid.topicPageTests;

import com.brainpop.pages.topics.healthAndSEL.DNA.MoviePage;
import com.brainpop.pages.topics.healthAndSEL.DNA.TopicPage;
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
        TopicPage topicPage = PageManager.getInstance().navigateToPage(TopicPage.class);
        MoviePage moviePage = topicPage.watchMovie();
        moviePage.playMovie();

        String movieProgress = moviePage.getMovieProgress();
        System.out.println("MOVIE PROGRESS VALUE = " + movieProgress);

    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testClosedCaptionActivation() throws InterruptedException {
        TopicPage topicPage = PageManager.getInstance().navigateToPage(TopicPage.class);
        MoviePage moviePage = topicPage.watchMovie();
        moviePage.playMovie();
        Thread.sleep(3000); //So Sorry to implement an explicit wait here. What is a better solution?
        moviePage.pauseMovie();

        moviePage.enableClosedCaptioning();
        String captions = moviePage.getCaptionText();
        Assert.assertNotNull(captions);
        moviePage.disableClosedCaptioning();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRelatedReadingFeatureSelection() {
        TopicPage topicPage = PageManager.getInstance().navigateToPage(TopicPage.class);
        topicPage.selectRelatedReadingFeature();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRandomFeatureSelection() {
        TopicPage topicPage = PageManager.getInstance().navigateToPage(TopicPage.class);
        topicPage.selectRandomFeature();
    }



    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
