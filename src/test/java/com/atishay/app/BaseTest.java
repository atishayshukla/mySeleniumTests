package com.atishay.app;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Atishay on 6/7/2015.
 */
public class BaseTest {

    protected static final String WEB_SERVER = System.getProperty("WEB_SERVER","naukri.com");
    protected static final boolean REMOTE_DRIVER = Boolean.valueOf(System.getProperty("REMOTE_DRIVER", "false"));
    protected static final String SELENIUM_HOST = System.getProperty("SELENIUM_HOST", "10.3.1.104");
    protected static final int SELENIUM_PORT = 4444;
    protected static final String BROWSER = System.getProperty("BROWSER", "chrome");

    public static RemoteWebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void suiteSetup(@Optional("chrome") String browser) throws Exception {
        // Check if the parameter from testng is firefox
        if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else  if (browser.equalsIgnoreCase("chrome")){

            String path = "D://tools/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        }
        else {
            throw new Exception("Browser not supported");

        }
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadLoginPage(){
        try {
            driver.get("http://" + WEB_SERVER);
            String mainWindow = driver.getWindowHandle();
            closeAllPopUpsDuringLoginPageLoad(mainWindow);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void testTearDown() {
        try {
            // some types of errors may leave a hanging "are you sure you want to leave this page" dialog
            // accept if it exists, ignore it otherwise
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // no alert
        }
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void suiteTearDown() {
        driver.quit();
    }

    public void closeAllPopUpsDuringLoginPageLoad(String mainWindow){
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String s : allWindowHandles){
            if (!s.equals(mainWindow)){
                driver.switchTo().window(s);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    public static void takeScreenshot(String testName) {
        SimpleDateFormat sDate = new SimpleDateFormat("HH-mm-ss");
        Date now = new Date();
        String currentTime = sDate.format(now);
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("build/reports/" + BROWSER + "/" + testName + "_" + BROWSER + "-" + currentTime + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
