package com.atishay.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Atishay on 6/7/2015.
 */
public class NaukriLoginPage extends AbstractPage {

    public NaukriLoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getUniqueElement() {
        return By.id("login_Layer");
    }

    By LoginButton = By.id("login_Layer");
    By emailIDSelectionLocator = By.id("eSel");
    By emailIDInputField = By.id("eLogin");
    By passwordInputField = By.id("pLogin");
    By LoginButtonOnCredsField = By.cssSelector("[value=Login]");

    public void clickLoginButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(LoginButton));
        driver.findElement(LoginButton).click();
    }
    public void selectEmailIDAsSelector(){
        wait.until(ExpectedConditions.presenceOfElementLocated(emailIDSelectionLocator));
        driver.findElement(emailIDSelectionLocator).click();
    }
    public void setEmailIDInputField(String email){
        driver.findElement(emailIDInputField).click();
        driver.findElement(emailIDInputField).clear();
        driver.findElement(emailIDInputField).sendKeys(email);
    }
    public void setPasswordInputField(String password){
        driver.findElement(passwordInputField).click();
        driver.findElement(passwordInputField).clear();
        driver.findElement(passwordInputField).sendKeys(password);
    }
    public HomePage ClickLoginButtonOnCredsField(){
        driver.findElement(LoginButtonOnCredsField).click();
        wait.until(ExpectedConditions.titleIs("Mynaukri"));
        return new HomePage(driver);
    }
    public String getTitle(){
        return driver.getTitle();
    }
}
