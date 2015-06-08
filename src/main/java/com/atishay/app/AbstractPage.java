package com.atishay.app;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Created by Atishay on 6/7/2015.
 */
public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(WebDriver driver){
        this(driver,10);
    }
    public AbstractPage(WebDriver driver, int timeOutInSeconds){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,timeOutInSeconds);
        PageFactory.initElements(driver,this);
    }

    protected abstract By getUniqueElement();

    protected void waitForPageToLoad(){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(getUniqueElement()));
        } catch (TimeoutException t) {
            System.out.print(t);
        }
    }
}
