package com.atishay.app.testNg;

import com.atishay.app.BaseTest;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by Atishay on 6/7/2015.
 */
public class Listener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.takeScreenshot(result.getTestName());
    }
}
