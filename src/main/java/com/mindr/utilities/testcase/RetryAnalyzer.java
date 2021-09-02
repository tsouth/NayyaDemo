package com.mindr.utilities.testcase;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (isRetryAvailable()) {
            retryCount++;
            return true;
        }
        return false;
    }

    public boolean isRetryAvailable() {
        return retryCount < 0;
    }
}